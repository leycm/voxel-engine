/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.util.uuid;

import java.util.UUID;

/**
 * Something that has a {@link java.util.UUID} identity.
 */
public interface VoxUuIdentifiable {

    /**
     * Get the {@link java.util.UUID}
     */
    UUID uuid();

}
