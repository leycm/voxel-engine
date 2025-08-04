package sync.voxel.engine.api.registry;

import sync.voxel.engine.api.material.VoxMaterialBehaviorRegistry;
import sync.voxel.engine.api.material.VoxMaterialRegistry;

public class VoxRegistries {
    public static final VoxMaterialRegistry MATERIAL = new VoxMaterialRegistry();
    public static final VoxMaterialBehaviorRegistry MATERIAL_GROUPS = new VoxMaterialBehaviorRegistry();

    private VoxRegistries() {} // utility class
}
