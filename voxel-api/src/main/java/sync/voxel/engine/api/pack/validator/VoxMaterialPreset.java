/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.pack.validator;

import org.jetbrains.annotations.ApiStatus;

public enum VoxMaterialPreset {
    SOLID_BLOCK(false, true),

    BLOCK_ENTITY(false, true),

    STAIR(false, true),

    SLAP(false, true),

    FANCE(false, true),

    FANCE_GATE(false, true),

    TRAPDOOR(false, true),

    DOOR(false, true),

    PLANT(false, true),

    ABSTRACT_MATERIAL(true, false),

    EATABLE_MATERIAL(true, false),

    RAW_MATERIAL(true, false),

    /**
     * Internal preset for vanilla-compatible materials.
     * Not intended for general use - has no custom model support and is not placeable.
     */
    @ApiStatus.Internal
    VANILLA(false, false);

    private final boolean customModel;
    private final boolean placeable;

    /**
     * Constructs a new material preset with the given properties.
     *
     * @param customModel whether this preset supports custom models
     * @param placeable whether blocks/items with this preset can be placed in the world
     */
    VoxMaterialPreset(boolean customModel, boolean placeable) {
        this.customModel = customModel;
        this.placeable = placeable;
    }

    public boolean hasCustomModel() {
        return customModel;
    }

    public boolean isPlaceable() {
        return placeable;
    }

}
