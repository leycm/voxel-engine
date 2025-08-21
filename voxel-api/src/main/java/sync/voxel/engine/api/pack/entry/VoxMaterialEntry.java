/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.pack.entry;

import sync.voxel.engine.api.identifier.interfaces.VoxIdentifiable;
import sync.voxel.engine.api.pack.validator.VoxMaterialPreset;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Interface for any Voxel material entry (like items, blocks, etc).
 */
public interface VoxMaterialEntry extends VoxRegistryEntry, VoxIdentifiable {

    /**
     * Returns the preset type for this material.
     *
     * @return the preset type
     */
    VoxMaterialPreset getPreset();

    /**
     * Returns the appearance configuration (textures/models).
     *
     * @return the appearance
     */
    VoxAppearanceEntry getAppearance();

    /**
     * Returns the material settings (rarity, saturation, etc).
     *
     * @return the settings
     */
    Map<String, Object> getSettings();

    /**
     * Returns optional raw NBT data associated with this material.
     *
     * @return optional NBT JSON string
     */
    Optional<String> getNbt();

    /**
     * Returns all potion effects associated with this material.
     *
     * @return map of triggers to effects
     */
    Map<String, Set<VoxEffectEntry>> getPotionEffects();

    /**
     * Returns all attribute modifications associated with this material.
     *
     * @return map of triggers to attribute/value pairs
     */
    Map<String, Set<VoxAttributeEntry>> getAttributes();
}

