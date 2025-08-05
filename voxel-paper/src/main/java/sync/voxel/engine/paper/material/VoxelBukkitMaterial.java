package sync.voxel.engine.paper.material;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sync.voxel.engine.api.group.VoxRenderGroupType;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.registry.VoxIdentifier;

public class VoxelBukkitMaterial implements VoxMaterial {
    private final VoxIdentifier identifier;
    private final Material vaMaterial;

    public VoxelBukkitMaterial(@NotNull Material vaMaterial) {
        this.identifier = VoxIdentifier.represent(vaMaterial.getKey());
        this.vaMaterial = vaMaterial;
    }

    @Override
    public @Nullable Material toVaMaterial() {
        return vaMaterial;
    }

    @Override
    public boolean isVanillaMaterial() {
        return true;
    }

    @Override
    public VoxRenderGroupType getVoxRenderType() {
        return VoxRenderGroupType.VANILLA; // TODO : impl this @LeyCM
    }

    @Override
    public <T> T getAttribute(String key, Class<T> type, T defaultValue) {
        return defaultValue;
    }

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }

}
