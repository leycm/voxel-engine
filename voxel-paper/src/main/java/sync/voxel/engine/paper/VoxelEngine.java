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
import sync.voxel.engine.common.logger.VoxelLogger;

public class VoxelEngine implements VoxEngine {
    public static VoxelLogger LOGGER = new VoxelLogger("VoxelEngine");
    public static boolean IS_BUILDING;
}
