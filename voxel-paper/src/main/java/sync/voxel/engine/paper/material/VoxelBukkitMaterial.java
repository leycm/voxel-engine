/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.material;

import org.bukkit.Material;

import sync.voxel.engine.api.pack.validator.VoxMaterialPreset;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.identifier.VoxIdentifier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public VoxMaterialPreset getMaterialPreset() {
        return VoxMaterialPreset.VANILLA;
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
