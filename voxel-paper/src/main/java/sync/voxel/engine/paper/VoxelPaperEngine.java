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

import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sync.voxel.engine.api.VoxEngine;
import sync.voxel.engine.api.common.VoxWorld;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.paper.listener.BukkitServerListener;
import sync.voxel.engine.paper.util.StackTraceCallerUtil;
import sync.voxel.engine.paper.util.translation.VoxelTranslator;
import sync.voxel.engine.paper.common.VoxelWorld;

import java.util.*;

public class VoxelPaperEngine implements VoxEngine {


    private final Logger mainLogger;
    private final VoxelTranslator translator;

    private final Map<UUID, VoxWorld> worlds = new HashMap<>();

    public VoxelPaperEngine() {
        this.mainLogger = LoggerFactory.getLogger("VoxelEngine");
        this.translator = new VoxelTranslator("plugins/voxel/pack/translations");
    }

    // ====== WORLD MANAGMENT ======

    @ApiStatus.Internal
    @Override
    public void registerWorld(UUID worldUid) throws UnsupportedOperationException {
        if (!StackTraceCallerUtil.iamCalledBy(BukkitServerListener.class))
            throw new UnsupportedOperationException("VoxEngine#registerWorld(UUID worldUid) can only be executed intern");

        VoxWorld world = VoxelWorld.port(Bukkit.getWorld(worldUid));
        if (worlds.containsKey(worldUid)) return;
        worlds.put(worldUid, world);
    }

    @ApiStatus.Internal
    @Override
    public void unregisterWorld(UUID worldUid) throws UnsupportedOperationException {
        if (!StackTraceCallerUtil.iamCalledBy(BukkitServerListener.class))
            throw new UnsupportedOperationException("VoxEngine#unregisterWorld(UUID worldUid) can only be executed intern");

        if (!worlds.containsKey(worldUid)) return;
        worlds.put(worldUid, null);
    }

    @Override
    public @Nullable VoxWorld getWorld(UUID worldUid) {
        return worlds.get(worldUid);
    }

    @Override
    public Collection<VoxWorld> getWorlds() {
        return worlds.values();
    }

    // ====== VOXEL ENGINE TUNNEL ======

    @Override
    public Logger logger() {
        return mainLogger;
    }

    @Override
    public String translate(String langCode, VoxIdentifier identifier) {
        return translator.translate(langCode, identifier);
    }

}
