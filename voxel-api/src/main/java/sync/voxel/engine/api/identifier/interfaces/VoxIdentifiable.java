/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.identifier.interfaces;

import sync.voxel.engine.api.identifier.VoxIdentifier;

/**
 * Something that has a {@link VoxIdentifier}.
 *
 * @since 1.0.1
 */
public interface VoxIdentifiable {

    /**
     * Returns the identifier {@link VoxIdentifier} of this object.
     *
     * @return the VoxIdentifier of this object
     *
     * @since 1.0.1
     */
    VoxIdentifier identifier();

    /**
     * Alias for {@link #identifier()}.
     * <p>
     * Provides a more descriptive method name for retrieving the {@link VoxIdentifier}.
     *
     * @return the VoxIdentifier of this object
     *
     * @since 1.0.1
     */
    default VoxIdentifier getIdentifier() {
        return identifier();
    }

    /**
     * Checks if the given {@link VoxIdentifier} matches this object's {@link #identifier()}.
     *
     * @param identifier the VoxIdentifier to compare with this object's VoxIdentifier
     * @return {@code true} if the given VoxIdentifier equals this object's VoxIdentifier, otherwise {@code false}
     *
     * @since 1.0.1
     */
    default boolean identify(VoxIdentifier identifier) {
        return identifier().equals(identifier);
    }

}
