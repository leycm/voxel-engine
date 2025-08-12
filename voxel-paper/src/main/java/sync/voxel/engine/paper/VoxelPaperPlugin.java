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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import org.bukkit.plugin.java.JavaPlugin;

import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.common.logger.VoxelLogLevel;
import sync.voxel.engine.paper.resourcepack.builder.VoxelResourcePackBuilder;
import sync.voxel.engine.paper.resourcepack.converter.VoxelVanillaConverter;

public class VoxelPaperPlugin extends JavaPlugin {

    public static VoxelPaperPlugin plugin;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {

        VoxelPaperPlugin.plugin = this;
        VoxelEngine.register(new VoxelPaperEngine());
        VoxelPaperEngine.LOGGER.setLevel(VoxelLogLevel.DEBUG);
        PacketEvents.getAPI().init();

        VoxelVanillaConverter.convertVanilla();
        VoxelResourcePackBuilder.buildPack("startup");

    }

    @Override
    public void onDisable() {
        VoxelEngine.unregister();
        PacketEvents.getAPI().terminate();
    }

}
