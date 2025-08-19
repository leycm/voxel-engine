/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.registry;

import org.bukkit.NamespacedKey;
import sync.voxel.engine.api.identifier.interfaces.VoxIdentifiable;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A dynamic registry system that manages uniquely identifiable elements,
 * similar to an {@code enum}, but allowing runtime registration.
 *
 * @param <VRI> the type of elements stored in the registry,
 *              must implement {@link VoxIdentifiable}.
 */
public interface VoxRegistry<VRI extends VoxIdentifiable> {

    /** Global registry for voxel materials. */
    VoxRegistries.MaterialRegistry MATERIALS = new VoxRegistries.MaterialRegistry();

    /** Global registry for voxel material behavior tags. */
    VoxRegistries.MaterialBehaviorTagRegistry MATERIALTAGS = new VoxRegistries.MaterialBehaviorTagRegistry();

    /** Global registry for voxel namespaces. */
    VoxRegistries.NameSpaceRegistry NAMESPACES = new VoxRegistries.NameSpaceRegistry();

    /**
     * Registers a new element in this registry.
     *
     * @param element the element to register
     * @throws IllegalArgumentException if an element with the same identifier is already present
     */
    void register(@NotNull VRI element);

    /**
     * Looks up an element by its {@link VoxIdentifier}.
     *
     * @param id the identifier of the element
     * @return the matching element, or {@code null} if none is found
     */
    VRI valueOf(@NotNull VoxIdentifier id);

    /**
     * Looks up an element by its {@link NamespacedKey}.
     *
     * @param identifier the namespaced key of the element
     * @return the matching element, or {@code null} if none is found
     */
    VRI valueOf(@NotNull NamespacedKey identifier);

    /**
     * Checks if this registry contains an element with the given identifier.
     *
     * @param id the identifier to check
     * @return {@code true} if the registry contains an element with this identifier,
     *         {@code false} otherwise
     */
    boolean contains(@NotNull VoxIdentifier id);

    /**
     * Returns all registered elements in the order they were inserted.
     *
     * @return a collection of all registered elements
     */
    Collection<VRI> values();

    /**
     * Returns the number of registered elements.
     *
     * @return the size of the registry
     */
    int size();

    /**
     * Removes all elements from this registry.
     */
    void clear();
}
