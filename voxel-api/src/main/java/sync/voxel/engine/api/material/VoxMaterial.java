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
import sync.voxel.engine.api.group.VoxRenderGroupType;
import sync.voxel.engine.api.registry.VoxIdentifiable;

/**
 * Represents a custom material in the Voxel engine that is linked to a Bukkit {@link Material}.
 * <p>
 * VoxMaterials are identifiable, map to a Minecraft {@link Material}, and can store custom attributes.
 */
public interface VoxMaterial extends VoxIdentifiable {

    /**
     * Convert to a default {@link org.bukkit.Material}
     * <p>
     * Cannot be null or an {@link org.bukkit.Material}  like AIR except its {@code minecraft:air}.
     */
    Material toVaMaterial();

    boolean isVanillaMaterial();

    VoxRenderGroupType getVoxRenderType();

    /**
     * Gets a specific setting value from this material's configuration.
     *
     * @param key The setting key to retrieve
     * @param type The class type of the setting value
     * @param defaultValue The default value to return if not found
     * @param <T> The type of the setting value
     * @return The setting value or defaultValue if not found
     */
    <T> T getAttribute(String key, Class<T> type, T defaultValue);
}
