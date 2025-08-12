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
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.util.namespace.VoxNameSpace;
import sync.voxel.engine.api.util.identifier.VoxIdentifiable;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A dynamic registry similar to an enum, holding uniquely identifiable elements.
 *
 * @param <VRI> the type of elements must implement VoxIdentifiable
 */
public class VoxRegistry<VRI extends VoxIdentifiable> {

    protected final Map<VoxIdentifier, VRI> entries = new LinkedHashMap<>();

    /**
     * Registers an element into the registry.
     *
     * @param element the element to add
     * @throws IllegalArgumentException if an element with the same identifier is already registered
     */
    public void register(@NotNull VRI element) {
        VoxIdentifier identifier = element.identifier();
        VoxNameSpace nameSpace = VoxNameSpace.of(identifier.namespace());

        if (!VoxRegistries.NAME_SPACES.contains(nameSpace.identifier())) {
            VoxRegistries.NAME_SPACES.register(nameSpace);
        }

        if (entries.containsKey(identifier)) {
            throw new IllegalArgumentException("Identifier already registered: " + identifier);
        }

        entries.put(identifier, element);
    }

    /**
     * Returns an element by its identifier.
     *
     * @param id the identifier
     * @return the element, or null if not found
     */
    public VRI valueOf(@NotNull VoxIdentifier id) {
        return entries.get(id);
    }

    /**
     * Returns an element by its identifier.
     *
     * @param identifier the identifier
     * @return the element, or null if not found
     */
    public VRI valueOf(@NotNull NamespacedKey identifier) {
        return entries.get(VoxIdentifier.represent(identifier));
    }

    /**
     * Returns true if the registry contains an element with this identifier.
     */
    public boolean contains(@NotNull VoxIdentifier id) {
        return entries.keySet().stream().anyMatch(key -> key.equals(id));
    }

    /**
     * Returns all registered elements in insertion order.
     */
    public Collection<VRI> values() {
        return Collections.unmodifiableCollection(entries.values());
    }

    /**
     * Returns the number of registered elements.
     */
    public int size() {
        return entries.size();
    }

    /**
     * Clears all registered elements.
     */
    public void clear() {
        entries.clear();
    }
}
