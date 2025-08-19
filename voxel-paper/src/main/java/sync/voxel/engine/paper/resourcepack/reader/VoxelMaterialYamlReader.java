package sync.voxel.engine.paper.resourcepack.reader;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import sync.voxel.engine.api.resourcepack.validator.VoxMaterialPresets;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.paper.resourcepack.entry.VoxPackMaterialEntry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

public class VoxelMaterialYamlReader {

    private static final Path REGISTRY_PATH = Paths.get("plugins/voxel/pack/registry");

    public Map<String, List<VoxPackMaterialEntry>> readAllMaterials() throws IOException {
        Map<String, List<VoxPackMaterialEntry>> materialsByNamespace = new HashMap<>();

        if (!Files.exists(REGISTRY_PATH) || !Files.isDirectory(REGISTRY_PATH)) {
            throw new IOException("Registry path not found or not a directory: " + REGISTRY_PATH);
        }

        try (DirectoryStream<Path> namespaceDirs = Files.newDirectoryStream(REGISTRY_PATH)) {
            for (Path nsDir : namespaceDirs) {
                if (!Files.isDirectory(nsDir)) continue;

                Path materialFile = nsDir.resolve("material.yml");
                if (Files.exists(materialFile)) continue;

                List<VoxPackMaterialEntry> entries = readMaterialFile(materialFile);
                materialsByNamespace.put(nsDir.getFileName().toString(), entries);
            }
        }

        return materialsByNamespace;
    }

    private @NotNull List<VoxPackMaterialEntry> readMaterialFile(@NotNull Path materialFile) throws IOException {
        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
        try (InputStream in = Files.newInputStream(materialFile)) {
            Object loaded = yaml.load(in);
            if (!(loaded instanceof Map)) {
                throw new IOException("Invalid material.yml format, expected Map at root.");
            }

            //noinspection unchecked
            Map<String, Object> rootMap = (Map<String, Object>) loaded;
            List<VoxPackMaterialEntry> entries = new ArrayList<>();

            for (Map.Entry<String, Object> e : rootMap.entrySet()) {
                Object value = e.getValue();
                String[] materialId = e.getKey().split(":");

                VoxIdentifier identifier = VoxIdentifier.of(materialId[0], materialId[1], "material");

                if (!(value instanceof Map)) {
                    throw new IOException("Material entry for " + identifier + " is not a Map.");
                }

                //noinspection unchecked
                VoxPackMaterialEntry entry = parseMaterialEntry(identifier, (Map<String, Object>) value);
                entries.add(entry);
            }

            return entries;
        }
    }

    private @NotNull VoxPackMaterialEntry parseMaterialEntry(VoxIdentifier identifier, @NotNull Map<String, Object> data) {
        VoxPackMaterialEntry entry = new VoxPackMaterialEntry();
        entry.setIdentifier(identifier);

        Object presetRaw = data.get("preset");
        if (presetRaw instanceof String) {
            try {
                VoxMaterialPresets preset = VoxMaterialPresets.valueOf(((String) presetRaw).toUpperCase(Locale.ROOT));
                entry.setPreset(preset);
            } catch (IllegalArgumentException ignored) {
                entry.setPreset(null);
            }
        }

        if (data.containsKey("appearance") && data.get("appearance") instanceof Map) {
            //noinspection unchecked
            Map<String, Object> rawAppearance = (Map<String, Object>) data.get("appearance");
            Map<String, File> converted = new HashMap<>();
            for (Map.Entry<String, Object> ap : rawAppearance.entrySet()) {
                if (ap.getValue() instanceof String) {
                    converted.put(ap.getKey(), new File((String) ap.getValue()));
                }
            }
            entry.setAppearance(converted);
        }

        if (data.containsKey("settings") && data.get("settings") instanceof Map) {
            //noinspection unchecked
            entry.setSettings((Map<String, Object>) data.get("settings"));
        }

        if (data.containsKey("potion_effects") && data.get("potion_effects") instanceof Map) {
            //noinspection unchecked
            entry.setPotion_effects((Map<String, Object>) data.get("potion_effects"));
        }

        if (data.containsKey("attributes") && data.get("attributes") instanceof Map) {
            //noinspection unchecked
            entry.setAttributes((Map<String, Object>) data.get("attributes"));
        }

        return entry;
    }

}
