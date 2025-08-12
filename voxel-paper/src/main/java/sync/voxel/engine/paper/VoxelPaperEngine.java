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
import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.api.world.VoxWorld;
import sync.voxel.engine.common.logger.VoxelLogger;
import sync.voxel.engine.common.translation.TranslationManager;

import java.io.File;
import java.util.*;

public class VoxelPaperEngine implements VoxEngine {

    public static VoxelLogger LOGGER = new VoxelLogger("VoxelEngine");
    public static boolean IS_BUILDING;

    private static final Set<VoxWorld> worlds = new HashSet<>();
    private final TranslationManager translationManager = new TranslationManager(new File("plugins/voxel/pack/translations"), LOGGER);

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

    @Override
    public String translate(String langCode, VoxIdentifier identifier) {
        return translationManager.translate(langCode, identifier);
    }

}
