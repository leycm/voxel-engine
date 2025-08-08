/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper;

import sync.voxel.engine.api.VoxEngine;
import sync.voxel.engine.api.world.VoxWorld;
import sync.voxel.engine.common.logger.VoxelLogger;

import java.util.*;

public class VoxelPaperEngine implements VoxEngine {

    public static VoxelLogger LOGGER = new VoxelLogger("VoxelEngine");
    public static boolean IS_BUILDING;

    public static Set<VoxWorld> worlds = new HashSet<>();

    @Override
    public VoxWorld getWorld(UUID uuid) {
        for (VoxWorld world : worlds)
            if (world.uuid().equals(uuid))
                return world;

        return null;
    }

    @Override
    public Collection<VoxWorld> getWorlds() {
        return Collections.unmodifiableSet(worlds);
    }

}
