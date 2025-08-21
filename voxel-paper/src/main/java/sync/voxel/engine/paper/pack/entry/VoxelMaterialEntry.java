package sync.voxel.engine.paper.pack.entry;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.api.pack.entry.VoxAppearanceEntry;
import sync.voxel.engine.api.pack.validator.VoxMaterialPreset;

import java.util.*;

@ToString
public class VoxelMaterialEntry implements sync.voxel.engine.api.pack.entry.VoxMaterialEntry {
    private VoxMaterialPreset preset;
    private VoxelAppearanceEntry appearance;
    private final Map<String, Object> settings = new HashMap<>();
    private String nbt;
    private final Map<String, Set<sync.voxel.engine.api.pack.entry.VoxEffectEntry>> potionEffects = new HashMap<>();
    private final Map<String, Set<sync.voxel.engine.api.pack.entry.VoxAttributeEntry>> attributes = new HashMap<>();
    private VoxIdentifier identifier;

    @Override
    public VoxMaterialPreset getPreset() {
        return preset;
    }

    @Override
    public VoxAppearanceEntry getAppearance() {
        return appearance;
    }

    @Override
    public Map<String, Object> getSettings() {
        return new HashMap<>(settings);
    }

    @Override
    public Optional<String> getNbt() {
        return Optional.ofNullable(nbt);
    }

    @Override
    public Map<String, Set<sync.voxel.engine.api.pack.entry.VoxEffectEntry>> getPotionEffects() {
        return new HashMap<>(potionEffects);
    }

    @Override
    public Map<String, Set<sync.voxel.engine.api.pack.entry.VoxAttributeEntry>> getAttributes() {
        return new HashMap<>(attributes);
    }

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }

    @Override
    public void parseFrom(String input) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> rootData = yaml.load(input);

            if (rootData == null || rootData.isEmpty()) return;

            Map.Entry<String, Object> materialEntry = rootData.entrySet().iterator().next();
            String materialId = materialEntry.getKey();

            @SuppressWarnings("unchecked")
            Map<String, Object> materialData = (Map<String, Object>) materialEntry.getValue();

            this.identifier = VoxIdentifier.parse(materialId);

            if (materialData.containsKey("preset")) {
                String presetName = (String) materialData.get("preset");
                this.preset = VoxMaterialPreset.valueOf(presetName);
            }

            if (materialData.containsKey("appearance")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> appearanceData = (Map<String, Object>) materialData.get("appearance");
                this.appearance = new VoxelAppearanceEntry();

                Yaml appearanceYaml = new Yaml();
                String appearanceString = appearanceYaml.dump(appearanceData);
                this.appearance.parseFrom(appearanceString);
            }

            if (materialData.containsKey("settings")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> settingsData = (Map<String, Object>) materialData.get("settings");
                this.settings.putAll(settingsData);
            }

            if (materialData.containsKey("nbt")) {
                this.nbt = (String) materialData.get("nbt");
            }

            if (materialData.containsKey("potion_effects")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> effectsData = (Map<String, Object>) materialData.get("potion_effects");

                for (Map.Entry<String, Object> contextEntry : effectsData.entrySet()) {
                    String context = contextEntry.getKey();
                    Set<sync.voxel.engine.api.pack.entry.VoxEffectEntry> effects = getVoxEffectEntries(contextEntry);
                    this.potionEffects.put(context, effects);
                }
            }

            if (materialData.containsKey("attributes")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> attributesData = (Map<String, Object>) materialData.get("attributes");

                for (Map.Entry<String, Object> contextEntry : attributesData.entrySet()) {
                    String context = contextEntry.getKey();
                    Set<sync.voxel.engine.api.pack.entry.VoxAttributeEntry> attrs = getVoxAttributeEntries(contextEntry);
                    this.attributes.put(context, attrs);
                }
            }

        } catch (Exception e) {
            VoxelEngine.getLogger().error("There was an error parsing", e);
        }
    }

    private static @NotNull Set<sync.voxel.engine.api.pack.entry.VoxEffectEntry> getVoxEffectEntries(Map.@NotNull Entry<String, Object> contextEntry) {
        @SuppressWarnings("unchecked")
        Map<String, Object> effectMap = (Map<String, Object>) contextEntry.getValue();

        Set<sync.voxel.engine.api.pack.entry.VoxEffectEntry> effects = new HashSet<>();
        for (Map.Entry<String, Object> effectEntry : effectMap.entrySet()) {
            VoxelEffectEntry effect = new VoxelEffectEntry();
            Map<String, Object> singleEffect = Map.of(effectEntry.getKey(), effectEntry.getValue());

            Yaml effectYaml = new Yaml();
            String effectString = effectYaml.dump(singleEffect);
            effect.parseFrom(effectString);
            effects.add(effect);
        }
        return effects;
    }

    private static @NotNull Set<sync.voxel.engine.api.pack.entry.VoxAttributeEntry> getVoxAttributeEntries(Map.@NotNull Entry<String, Object> contextEntry) {
        @SuppressWarnings("unchecked")
        Map<String, Object> attributeMap = (Map<String, Object>) contextEntry.getValue();

        Set<sync.voxel.engine.api.pack.entry.VoxAttributeEntry> attrs = new HashSet<>();
        for (Map.Entry<String, Object> attrEntry : attributeMap.entrySet()) {
            VoxelAttributeEntry attribute = new VoxelAttributeEntry();
            Map<String, Object> singleAttr = Map.of(attrEntry.getKey(), attrEntry.getValue());

            Yaml attrYaml = new Yaml();
            String attrString = attrYaml.dump(singleAttr);
            attribute.parseFrom(attrString);
            attrs.add(attribute);
        }
        return attrs;
    }
}