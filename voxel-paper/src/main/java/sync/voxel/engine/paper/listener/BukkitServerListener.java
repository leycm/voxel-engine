/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import sync.voxel.engine.api.VoxelEngine;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BukkitServerListener implements Listener {

    @EventHandler
    public void onWorldLoad(@NotNull WorldLoadEvent event) {
        UUID worldUid = event.getWorld().getUID();
        VoxelEngine.getEngine().registerWorld(worldUid);
    }

    @EventHandler
    public void onWorldUnload(@NotNull WorldUnloadEvent event) {
        UUID worldUid = event.getWorld().getUID();
        VoxelEngine.getEngine().unregisterWorld(worldUid);
    }

    @EventHandler
    public void onPluginDisable(@NotNull PluginDisableEvent event) {

    }

}
