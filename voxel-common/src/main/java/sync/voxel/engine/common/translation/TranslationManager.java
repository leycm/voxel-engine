package sync.voxel.engine.common.translation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.common.logger.VoxelLogger;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TranslationManager {

    private final Map<String, Map<String, String>> langCache = new HashMap<>();
    private final Gson gson = new Gson();

    private final File translationsDir;
    private final VoxelLogger logger;

    public TranslationManager(@NotNull File translationsDir, VoxelLogger logger)  {
        this.translationsDir = translationsDir;
        this.logger = logger;

        if (!translationsDir.exists()) translationsDir.mkdirs();
    }

    public String translate(String langCode, @NotNull VoxIdentifier identifier) {
        loadLanguage(langCode);
        loadLanguage("en_us");

        String fullKey = identifier.toString(':', 2);

        Map<String, String> langMap = langCache.get(langCode);
        if (langMap != null && langMap.containsKey(fullKey)) {
            return langMap.get(fullKey);
        }

        Map<String, String> enMap = langCache.get("en");
        if (enMap != null && enMap.containsKey(fullKey)) {
            return enMap.get(fullKey);
        }

        return fullKey;
    }

    private void loadLanguage(String langCode) {
        if (langCache.containsKey(langCode)) return;

        Map<String, String> translations = new HashMap<>();

        Map<String, String> remoteMap = downloadJson(
                "https://voxelsync.github.io/translation/minecraft/" + langCode
        );

        if (remoteMap != null) translations.putAll(remoteMap);

        File localFile = new File(translationsDir, langCode + ".json");
        if (localFile.exists()) {
            try (Reader reader = new InputStreamReader(new FileInputStream(localFile), StandardCharsets.UTF_8)) {

                Type type = new TypeToken<Map<String, String>>() {}.getType();
                Map<String, String> localMap = gson.fromJson(reader, type);
                if (localMap != null) {
                    translations.putAll(localMap);
                }

            } catch (IOException e) {
                logger.error("Error while reading from \"{}\" \n{}:", localFile.toString(), e.getMessage(), e);
            }
        }

        langCache.put(langCode, translations);
    }

    private @Nullable Map<String, String> downloadJson(String urlStr) {
        try {

            URL url = URL.of(URI.create(urlStr), null);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", logger.getPrefix());
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() != 200) return null;

            try (Reader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)) {
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                return gson.fromJson(reader, type);
            }

        } catch (IOException e) {
            logger.error("Error while reading from the web \"{}\" \n{}:", urlStr, e.getMessage(), e);
        }

        return null;
    }
}
