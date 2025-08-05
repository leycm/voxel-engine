package sync.voxel.engine.paper.material;

import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import sync.voxel.engine.api.group.VoxRenderGroupType;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.registry.VoxIdentifier;

public class VoxelMaterial implements VoxMaterial {

    @ApiStatus.Internal
    public VoxelMaterial() {}

    @Override
    public Material toVaMaterial() {
        return null;
    }

    @Override
    public boolean isVanillaMaterial() {
        return false;
    }

    @Override
    public VoxRenderGroupType getVoxRenderType() {
        return null;
    }

    @Override
    public <T> T getAttribute(String key, Class<T> type, T defaultValue) {
        return null;
    }

    @Override
    public VoxIdentifier identifier() {
        return null;
    }
}
