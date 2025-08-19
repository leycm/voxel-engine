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

/**
 * Something that has a {@link VoxIdentifier}.
 *
 * @since 1.0.1
 */
public interface VoxIdentifiable {

    /**
     * Get the {@link VoxIdentifier} of the object.
     *
     * @since 1.0.1
     */
    VoxIdentifier identifier();

    /**
     * Get the {@link VoxIdentifier} of the object.
     * This only is an alt for {@link VoxIdentifiable#identifier()}
     *
     * @since 1.0.1
     */
    default VoxIdentifier getIdentifier() {
        return identifier();
    }

    /**
     * Identify if the object ist the one you want
     *
     * @param identifier the identifier to identify
     * @return is {@code identifier} the identifier of the object
     *
     * @since 1.0.1
     */
    default boolean identify(VoxIdentifier identifier) {
        return identifier().equals(identifier);
    }

}
