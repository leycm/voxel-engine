package sync.voxel.engine.api.group;

import org.jetbrains.annotations.ApiStatus;

public enum VoxRenderGroupType {
    SOLID_BLOCK,

    STAIR_BLOCK,

    HALF_BLOCK,

    PLANT_BLOCK,

    BLOCK_ENTITY,

    ABSTRACT_ITEM,

    EATABLE_ITEM,

    EMPTY_ITEM,

    @ApiStatus.Internal
    VANILLA
}
