package sync.voxel.engine.paper.pack.entry;

import lombok.ToString;
import org.yaml.snakeyaml.Yaml;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.api.pack.entry.VoxEffectEntry;

import java.util.Map;

@ToString
public class VoxelEffectEntry implements VoxEffectEntry {
    private String effect = "";
    private int amplifier = 0;

    @Override
    public String getEffect() {
        return effect;
    }

    @Override
    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public void parseFrom(String input) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(input);

            if (data == null) return;

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                this.effect = entry.getKey();

                if (entry.getValue() instanceof Number) {
                    this.amplifier = ((Number) entry.getValue()).intValue();
                } else if (entry.getValue() instanceof String) {
                    try {
                        this.amplifier = Integer.parseInt((String) entry.getValue());
                    } catch (NumberFormatException e) {
                        this.amplifier = 0;
                    }
                }
                break;
            }
        } catch (Exception e) {
            VoxelEngine.getLogger().error("There was an error parsing", e);
        }
    }
}