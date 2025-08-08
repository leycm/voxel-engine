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

import sync.voxel.engine.api.util.group.VoxBehaviorGroup;
import sync.voxel.engine.api.registry.VoxRegistry;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry class for managing groups of {@link VoxMaterial}.
 * <p>
 * Each material can belong to one or more {@link VoxBehaviorGroup}s.
 * This registry allows you to retrieve all groups a material is part of.
 */
public class VoxMaterialBehaviorRegistry extends VoxRegistry<VoxBehaviorGroup<VoxMaterial>> {

    @ApiStatus.Internal
    public VoxMaterialBehaviorRegistry() {}

    /**
     * Returns all registered {@link VoxBehaviorGroup}s that contain the given material.
     *
     * @param material the material to check
     * @return an array of material groups that contain the specified material
     */
    public VoxBehaviorGroup<VoxMaterial>[] valuesFor(VoxMaterial material) {
        List<VoxBehaviorGroup<VoxMaterial>> groups = new ArrayList<>();

        entries.forEach(((identifier, group) -> {
            if (group.contains(material)) groups.add(group);
        }));

        //noinspection unchecked
        return groups.toArray(new VoxBehaviorGroup[0]);
    }

}
