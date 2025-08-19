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
import sync.voxel.engine.api.identifier.VoxNameSpace;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class VoxItemRegistry<VRI extends VoxIdentifiable> implements VoxRegistry<VRI> {

    protected final Map<VoxIdentifier, VRI> entries = new ConcurrentHashMap<>();

    @Override
    public void register(@NotNull VRI element) {
        VoxIdentifier identifier = element.identifier();
        VoxNameSpace nameSpace = VoxNameSpace.of(identifier.namespace());

        if (!VoxRegistry.NAMESPACES.contains(nameSpace.identifier())) {
            VoxRegistry.NAMESPACES.register(nameSpace);
        }

        if (entries.containsKey(identifier)) {
            throw new IllegalArgumentException("Identifier already registered: " + identifier);
        }

        entries.put(identifier, element);
    }

    @Override
    public VRI valueOf(@NotNull VoxIdentifier id) {
        return Objects.requireNonNull(entries.get(id), "Ops.. there is no Registry entry for \"" + id + "\"");
    }

    @Override
    public VRI valueOf(@NotNull NamespacedKey identifier) {
        return Objects.requireNonNull(entries.get(VoxIdentifier.represent(identifier)), "Ops.. there is no Registry entry for \"" + identifier + "\"");
    }

    @Override
    public boolean contains(@NotNull VoxIdentifier id) {
        return entries.keySet().stream().anyMatch(key -> key.equals(id));
    }

    @Override
    public Collection<VRI> values() {
        return Collections.unmodifiableCollection(entries.values());
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public void clear() {
        entries.clear();
    }
}
