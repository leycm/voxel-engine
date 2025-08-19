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


import sync.voxel.engine.api.registry.VoxBehaviorTag;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.identifier.VoxIdentifier;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class VoxMaterialBehaviorGroup implements VoxBehaviorTag<VoxMaterial> {

    private final Map<VoxIdentifier, VoxMaterial> elements;
    private final VoxIdentifier identifier;

    public VoxMaterialBehaviorGroup(VoxIdentifier identifier) {
        this.identifier = identifier;
        this.elements = new HashMap<>();
    }

    @Override
    public void add(@NotNull VoxMaterial element) {
        if (!elements.containsKey(element.identifier())) elements.put(element.identifier(), element);
    }

    @Override
    public void remove(@NotNull VoxMaterial element) {
        elements.remove(element.identifier());
    }

    @Override
    public boolean contains(@NotNull VoxMaterial element) {
        return elements.containsKey(element.identifier());
    }

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }
}
