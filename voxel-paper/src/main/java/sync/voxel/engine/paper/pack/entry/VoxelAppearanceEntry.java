package sync.voxel.engine.paper.pack.entry;

import lombok.ToString;
import org.yaml.snakeyaml.Yaml;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.api.pack.entry.VoxAppearanceEntry;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ToString
public class VoxelAppearanceEntry implements VoxAppearanceEntry {
    private File model;
    private Map<String, File> layers = new HashMap<>();

    @Override
    public Optional<File> getModel() {
        return Optional.ofNullable(model);
    }

    @Override
    public File getLayer(String layer) {
        return layers.get(layer);
    }

    @Override
    public Map<String, File> getLayers() {
        return new HashMap<>(layers);
    }

    @Override
    public void parseFrom(String input) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(input);

            if (data == null) return;

            if (data.containsKey("model")) {
                String modelPath = (String) data.get("model");
                this.model = new File("models/" + modelPath + ".json");
            }

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if ("model".equals(key)) continue;

                if (value instanceof String texturePath) {

                    if (key.equals("all")) {
                        String[] commonLayers = {"layer0", "layer1", "layer2", "layer3", "particle"};
                        for (String layer : commonLayers) {
                            layers.put(layer, new File("textures/" + texturePath + ".png"));
                        }
                    } else {
                        layers.put(key, new File("textures/" + texturePath + ".png"));
                    }
                }
            }
        } catch (Exception e) {
            VoxelEngine.getLogger().error("There was an error parsing", e);
        }
    }
}