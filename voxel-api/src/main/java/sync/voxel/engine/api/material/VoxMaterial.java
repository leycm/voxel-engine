/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.material;

import org.bukkit.Material;
import sync.voxel.engine.api.pack.validator.VoxMaterialPreset;
import sync.voxel.engine.api.identifier.interfaces.VoxIdentifiable;

/**
 * Represents a material definition within the Voxel engine.
 * <p>
 * A {@code VoxMaterial} is an identifiable resource that is associated with a
 * Minecraft {@link Material}, while allowing additional custom attributes and rendering presets.
 * <p>
 * Implementations may represent both vanilla Minecraft materials and custom ones defined by the engine.
 *
 * @since 1.0.1
 */
public interface VoxMaterial extends VoxIdentifiable {

    /**
     * Returns the corresponding vanilla {@link Material} for this material.
     * <p>
     * This method never returns {@code null}. The returned {@link Material} must be a valid Bukkit
     * material and cannot be a placeholder such as {@link Material#AIR}, unless the identifier is
     * explicitly {@code minecraft:air}.
     *
     * @return the linked {@link Material}, never {@code null}
     *
     * @since 1.0.1
     */
    Material toVaMaterial();

    /**
     * Checks whether this material represents a vanilla Minecraft material.
     * <p>
     * Vanilla materials map directly to {@link Material} values without custom overrides.
     *
     * @return {@code true} if this is a vanilla material, {@code false} if it is custom
     *
     * @since 1.0.1
     */
    boolean isVanillaMaterial();

    /**
     * Returns the {@link VoxMaterialPreset} preset type for this material.
     * <p>
     * The render type defines how the material is handled in resource packs
     * (e.g. cutout, translucent, solid).
     *
     * @return the {@link VoxMaterialPreset} defining the render type
     *
     * @since 1.0.1
     */
    VoxMaterialPreset getMaterialPreset();

    /**
     * Retrieves a custom attribute value for this material.
     * <p>
     * Attributes allow storing additional metadata, such as custom rendering properties,
     * interaction rules, or engine-specific flags.
     *
     * @param key          the attribute key to look up
     * @param type         the expected type of the attribute value
     * @param defaultValue the value to return if the attribute is not found or cannot be converted
     * @param <T>          the type of the attribute value
     * @return the attribute value if present and compatible with {@code type},
     *         otherwise {@code defaultValue}
     *
     * @since 1.0.1
     */
    <T> T getAttribute(String key, Class<T> type, T defaultValue);
}
