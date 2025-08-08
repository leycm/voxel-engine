/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.material;

import org.bukkit.Material;

import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.api.registry.VoxRegistry;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class VoxMaterialRegistry extends VoxRegistry<VoxMaterial> {

    @ApiStatus.Internal
    public VoxMaterialRegistry() {}

    public VoxMaterial valueOf(@NotNull Material vaMaterial) {
        return valueOf(VoxIdentifier.represent(vaMaterial.getKey()));
    }
}
