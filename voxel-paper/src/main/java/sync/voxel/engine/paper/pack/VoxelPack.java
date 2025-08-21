/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.pack;

import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.paper.pack.manager.VoxelPackProvider;
import sync.voxel.engine.paper.pack.manager.VoxelPackReader;

/**
 * Utility class for building and managing the voxel resource pack.
 */
public class VoxelPack {
    private static final VoxelPackProvider provider = new VoxelPackProvider();
    private static final VoxelPackReader reader = new VoxelPackReader();


    /**
     * Builds the resource pack by ensuring required files are in place.
     */
    public static void buildPack(String reason) {
        long startTime = System.currentTimeMillis();
        VoxelEngine.getLogger().info("Starting pack building with reason \"{}\"", reason);

        provider.provideDefaultPack();


        long duration = System.currentTimeMillis() - startTime;
        VoxelEngine.getLogger().info("Done building pack {}", duration);

    }

    private static @NotNull String formatDuration(long millis) {
        long minutes = millis / 60000;
        long seconds = (millis % 60000) / 1000;
        long ms = millis % 1000;

        StringBuilder sb = new StringBuilder("after ");
        if (minutes > 0) sb.append(minutes).append("m ");
        if (seconds > 0) sb.append(seconds).append("s ");
        sb.append(ms).append("ms");
        return sb.toString();
    }

}
