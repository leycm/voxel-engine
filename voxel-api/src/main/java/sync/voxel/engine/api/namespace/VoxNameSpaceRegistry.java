/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.namespace;

import sync.voxel.engine.api.registry.VoxRegistry;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VoxNameSpaceRegistry extends VoxRegistry<VoxNameSpace> {

    @Override
    public VoxNameSpace valueOf(@NotNull VoxIdentifier id) {
        for (Map.Entry<VoxIdentifier, VoxNameSpace> entry : entries.entrySet()) {
            if (entry.getKey().namespace().equalsIgnoreCase(id.namespace())) {
                return entry.getValue();
            }
        }

        return null;
    }

    public void register(@NotNull VoxNameSpace element) {
        VoxIdentifier identifier = element.identifier();

        if (entries.containsKey(identifier)) {
            throw new IllegalArgumentException("Identifier already registered: " + identifier);
        }

        entries.put(identifier, element);
    }

}
