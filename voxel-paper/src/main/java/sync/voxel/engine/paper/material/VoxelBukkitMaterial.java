package sync.voxel.engine.paper.material;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sync.voxel.engine.api.resourcepack.validator.VoxMaterialPresets;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;

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
    public VoxMaterialPresets getVoxRenderType() {
        return VoxMaterialPresets.VANILLA;
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
