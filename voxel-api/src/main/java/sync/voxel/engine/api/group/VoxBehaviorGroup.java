/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.group;

import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.registry.VoxIdentifiable;

/**
 * Represents a group of {@link VoxMaterial}s.
 * <p>
 * Material groups are useful for categorizing or applying bulk operations to sets of materials.
 */
public interface VoxBehaviorGroup<VGI extends  VoxIdentifiable> extends VoxIdentifiable {

    /**
     * Adds an element to the group.
     *
     * @param element the element to add
     */
    void add(VGI element);

    /**
     * Removes an element from the group.
     *
     * @param element the element to remove
     */
    void remove(VoxMaterial element);

    /**
     * Checks if the group contains the specified element.
     *
     * @param element the material to check
     * @return true if the element is in the group, false otherwise
     */
    boolean contains(VoxMaterial element);

}
