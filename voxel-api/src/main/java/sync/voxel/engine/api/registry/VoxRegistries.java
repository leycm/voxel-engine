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

import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sync.voxel.engine.api.material.VoxMaterial;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.api.identifier.VoxNameSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Central container for registry implementations used within the Voxel engine.
 * <p>
 * Registries are responsible for managing different categories of engine resources,
 * such as materials, behavior groups, or namespaces. Each registry associates
 * {@link VoxIdentifier identifiers} with corresponding values.
 *
 * @since 1.0.1
 */
public interface VoxRegistries {

    /**
     * A registry that manages {@link VoxBehaviorTag} groups for {@link VoxMaterial}.
     * <p>
     * Each behavior tag can group multiple materials based on shared properties
     * or behaviors (e.g. "flammable", "mineable_with_pickaxe").
     *
     * @since 1.0.1
     */
    class MaterialBehaviorTagRegistry extends VoxItemRegistry<VoxBehaviorTag<VoxMaterial>> {

        /**
         * Internal constructor. Should not be called directly.
         */
        @ApiStatus.Internal
        protected MaterialBehaviorTagRegistry() {}

        /**
         * Retrieves all registered {@link VoxBehaviorTag}s that contain the given material.
         *
         * @param material the material to check
         * @return an array of all behavior tags this material belongs to,
         *         or an empty array if none are found
         *
         * @since 1.0.1
         */
        public VoxBehaviorTag<VoxMaterial>[] valuesFor(VoxMaterial material) {
            List<VoxBehaviorTag<VoxMaterial>> groups = new ArrayList<>();

            entries.forEach(((identifier, group) -> {
                if (group.contains(material)) groups.add(group);
            }));

            //noinspection unchecked
            return groups.toArray(new VoxBehaviorTag[0]);
        }
    }

    /**
     * A registry that manages {@link VoxMaterial} definitions.
     * <p>
     * Provides lookup by {@link VoxIdentifier} as well as direct mapping from
     * Bukkit {@link Material}.
     *
     * @since 1.0.1
     */
    class MaterialRegistry extends VoxItemRegistry<VoxMaterial> {

        /**
         * Internal constructor. Should not be called directly.
         */
        @ApiStatus.Internal
        protected MaterialRegistry() {}

        /**
         * Resolves a {@link VoxMaterial} from its corresponding vanilla {@link Material}.
         *
         * @param vaMaterial the Bukkit material to resolve
         * @return the corresponding {@link VoxMaterial}, or {@code null} if not registered
         *
         * @since 1.0.1
         */
        public VoxMaterial valueOf(@NotNull Material vaMaterial) {
            return valueOf(VoxIdentifier.represent(vaMaterial.getKey()));
        }
    }

    /**
     * A registry that manages {@link VoxNameSpace} entries.
     * <p>
     * Unlike other registries, lookup is performed case-insensitively on the namespace
     * portion of the identifier.
     *
     * @since 1.0.1
     */
    class NameSpaceRegistry extends VoxItemRegistry<VoxNameSpace> {

        /**
         * Internal constructor. Should not be called directly.
         */
        @ApiStatus.Internal
        protected NameSpaceRegistry() {}

        /**
         * Retrieves a registered {@link VoxNameSpace} for the given identifier.
         * <p>
         * Matching is case-insensitive on the namespace component.
         *
         * @param id the identifier containing the namespace to search for
         * @return the matching {@link VoxNameSpace}, or {@code null} if not registered
         *
         * @since 1.0.1
         */
        @Override
        public @Nullable VoxNameSpace valueOf(@NotNull VoxIdentifier id) {
            for (Map.Entry<VoxIdentifier, VoxNameSpace> entry : entries.entrySet()) {
                if (entry.getKey().namespace().equalsIgnoreCase(id.namespace())) {
                    return entry.getValue();
                }
            }
            return null;
        }

        /**
         * Registers a new {@link VoxNameSpace}.
         *
         * @param element the namespace to register
         * @throws IllegalArgumentException if an element with the same identifier
         *                                  is already registered
         *
         * @since 1.0.1
         */
        public void register(@NotNull VoxNameSpace element) {
            VoxIdentifier identifier = element.identifier();

            if (entries.containsKey(identifier)) {
                throw new IllegalArgumentException("Identifier already registered: " + identifier);
            }

            entries.put(identifier, element);
        }
    }
}
