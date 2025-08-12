package sync.voxel.engine.paper.resourcepack.reader;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import sync.voxel.engine.api.resourcepack.validator.VoxMaterialPresets;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.paper.resourcepack.entry.VoxPackMaterialEntry;

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
                if (Files.isDirectory(nsDir)) {
                    Path materialFile = nsDir.resolve("material.yml");
                    if (Files.exists(materialFile)) {
                        List<VoxPackMaterialEntry> entries = readMaterialFile(materialFile, nsDir.getFileName().toString());
                        materialsByNamespace.put(nsDir.getFileName().toString(), entries);
                    }
                }
            }
        }

        return materialsByNamespace;
    }

    private List<VoxPackMaterialEntry> readMaterialFile(@NotNull Path materialFile, String namespace) throws IOException {
        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
        try (InputStream in = Files.newInputStream(materialFile)) {
            Object loaded = yaml.load(in);
            if (!(loaded instanceof Map)) {
                throw new IOException("Invalid material.yml format, expected Map at root.");
            }
            Map<String, Object> rootMap = (Map<String, Object>) loaded;
            List<VoxPackMaterialEntry> entries = new ArrayList<>();

            for (Map.Entry<String, Object> e : rootMap.entrySet()) {
                String keyPart = e.getKey();
                Object value = e.getValue();

                if (!(value instanceof Map)) {
                    throw new IOException("Material entry for " + keyPart + " is not a Map.");
                }

                String idString = namespace + ":" + Arrays.toString(keyPart.split(":"));
                VoxIdentifier identifier = VoxIdentifier.parse(keyPart);

                VoxPackMaterialEntry entry = parseMaterialEntry(identifier, (Map<String, Object>) value);
                entries.add(entry);
            }

            return entries;
        }
    }

    private VoxPackMaterialEntry parseMaterialEntry(VoxIdentifier identifier, Map<String, Object> data) {
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
            entry.setAppearance((Map<String, Object>) data.get("appearance"));
        }

        if (data.containsKey("settings") && data.get("settings") instanceof Map) {
            entry.setSettings((Map<String, Object>) data.get("settings"));
        }

        return entry;
    }
}
