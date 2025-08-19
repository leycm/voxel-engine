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

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sync.voxel.engine.api.VoxEngine;
import sync.voxel.engine.api.world.VoxWorld;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.paper.util.translation.VoxelTranslator;

import java.util.*;

public class VoxelPaperEngine implements VoxEngine {


    private final Logger mainLogger;
    private final VoxelTranslator translator;

    private final Set<VoxWorld> worlds = new HashSet<>();

    public VoxelPaperEngine() {
        this.mainLogger = LoggerFactory.getLogger("VoxelEngine");
        this.translator = new VoxelTranslator("plugins/voxel/pack/translations");
    }

    // ====== WORLD MANAGMENT ======

    @ApiStatus.Internal
    @Override
    public boolean registerWorld(VoxWorld world) {
        if (worlds.contains(world)) return false;

        worlds.add(world);
        return true;
    }

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
