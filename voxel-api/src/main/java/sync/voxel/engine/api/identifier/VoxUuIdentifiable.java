/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.identifier;

import java.util.UUID;

/**
 * Represents something that can be uniquely identified by a {@link UUID}.
 */
public interface VoxUuIdentifiable {

    /**
     * Returns the unique {@link UUID} of this object.
     *
     * @return the UUID of this object
     */
    UUID uuid();

    /**
     * Alias for {@link #uuid()}.
     * <p>
     * Provides a more descriptive method name for retrieving the UUID.
     *
     * @return the UUID of this object
     *
     * @since 1.0.1
     */
    default UUID getUuid() {
        return uuid();
    }

    /**
     * Checks if the given {@link UUID} matches this object's {@link #uuid()}.
     *
     * @param uuid the UUID to compare with this object's UUID
     * @return {@code true} if the given UUID equals this object's UUID, otherwise {@code false}
     *
     * @since 1.0.1
     */
    default boolean identify(UUID uuid) {
        return uuid().equals(uuid);
    }
}
