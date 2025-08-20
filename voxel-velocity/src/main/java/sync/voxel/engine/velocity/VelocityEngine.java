/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.velocity;

import org.slf4j.Logger;
import sync.voxel.engine.api.VoxEngine;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.api.world.VoxWorld;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class VelocityEngine implements VoxEngine {

    @Override
    public void registerWorld(UUID worldUid) {

    }

    @Override
    public void unregisterWorld(UUID worldUid) {

    }

    @Override
    public VoxWorld getWorld(UUID uuid) {
        return null;
    }

    @Override
    public Collection<VoxWorld> getWorlds() {
        return List.of();
    }

    @Override
    public Logger logger() {
        return null;
    }

    @Override
    public String translate(String langCode, VoxIdentifier identifier) {
        return "";
    }
}
