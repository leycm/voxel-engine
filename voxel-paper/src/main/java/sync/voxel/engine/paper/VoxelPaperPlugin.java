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

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;

import org.bukkit.plugin.java.JavaPlugin;

import org.slf4j.event.Level;
import com.djaytan.bukkit.slf4j.api.BukkitLoggerFactory;

import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.paper.resourcepack.builder.VoxelResourcePackBuilder;
import sync.voxel.engine.paper.resourcepack.converter.VoxelVanillaConverter;

public class VoxelPaperPlugin extends JavaPlugin {

    public static VoxelPaperPlugin plugin;

    @Override
    public void onLoad() {
        BukkitLoggerFactory.provideBukkitLogger(this.getLogger());
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        BukkitLoggerFactory.provideBukkitLogger(this.getLogger());
        PacketEvents.getAPI().init();

        VoxelPaperPlugin.plugin = this;
        VoxelEngine.register(new VoxelPaperEngine());
        VoxelEngine.logger().isEnabledForLevel(Level.DEBUG);

        VoxelVanillaConverter.convertVanilla();
        VoxelResourcePackBuilder.buildPack("startup");

    }

    @Override
    public void onDisable() {
        VoxelEngine.unregister();
        PacketEvents.getAPI().terminate();
    }

}
