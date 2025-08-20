/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api;

import org.slf4j.Logger;

import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.api.world.VoxWorld;

import java.util.Collection;
import java.util.UUID;

public interface VoxEngine {

    // ====== WORLD MANAGMENT ======

    void registerWorld(UUID worldUid);

    void unregisterWorld(UUID worldUid);

    VoxWorld getWorld(UUID worldUid);

    Collection<VoxWorld> getWorlds();

    // ====== VOXEL ENGINE TUNNEL ======

    Logger logger();

    String translate(String langCode, VoxIdentifier identifier);
}
