/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.registry;

import sync.voxel.engine.api.material.VoxMaterialBehaviorRegistry;
import sync.voxel.engine.api.material.VoxMaterialRegistry;
import sync.voxel.engine.api.namespace.VoxNameSpaceRegistry;

public class VoxRegistries {
    public static final VoxMaterialRegistry MATERIAL = new VoxMaterialRegistry();
    public static final VoxMaterialBehaviorRegistry MATERIAL_GROUPS = new VoxMaterialBehaviorRegistry();
    public static final VoxNameSpaceRegistry NAME_SPACES = new VoxNameSpaceRegistry();


    private VoxRegistries() {} // utility class
}
