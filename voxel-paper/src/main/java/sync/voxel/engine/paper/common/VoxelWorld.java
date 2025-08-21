/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.common;

import org.bukkit.World;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.common.VoxWorld;

import java.util.UUID;

public class VoxelWorld implements VoxWorld {

    private World world;

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VoxelWorld port(World world) {
        return new VoxelWorld(world);
    }

    private VoxelWorld (World world) {

    }

    @Override
    public UUID uuid() {
        return world.getUID();
    }
}
