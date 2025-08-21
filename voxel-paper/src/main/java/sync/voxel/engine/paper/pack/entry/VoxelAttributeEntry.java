package sync.voxel.engine.paper.pack.entry;

import lombok.ToString;
import org.yaml.snakeyaml.Yaml;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.api.pack.entry.VoxAttributeEntry;

import java.util.Map;

@ToString
public class VoxelAttributeEntry implements VoxAttributeEntry {
    private String attribute = "";
    private double value = 0.0;
    private String operation = "add";

    @Override
    public String getAttribute() {
        return attribute;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public void parseFrom(String input) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(input);

            if (data == null) return;

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                this.attribute = entry.getKey();

                if (entry.getValue() instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> attributeData = (Map<String, Object>) entry.getValue();

                    if (attributeData.containsKey("value")) {
                        Object valueObj = attributeData.get("value");
                        if (valueObj instanceof Number) {
                            this.value = ((Number) valueObj).doubleValue();
                        }
                    }

                    if (attributeData.containsKey("operation")) {
                        this.operation = (String) attributeData.get("operation");
                    }
                }
                break;
            }
        } catch (Exception e) {
            VoxelEngine.getLogger().error("There was an error parsing", e);
        }
    }
}