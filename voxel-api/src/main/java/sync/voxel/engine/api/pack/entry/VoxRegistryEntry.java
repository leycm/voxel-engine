/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.pack.entry;

/**
 * Represents a generic entry in the Voxel material registry.
 * <p>
 * Every entry can be parsed from a YAML/JSON string, and has a unique identifier.
 */
public interface VoxRegistryEntry {

    /**
     * Parses this entry from a raw string (YAML/JSON/etc).
     *
     * @param input the raw input string
     * @throws IllegalArgumentException if parsing fails
     */
    void parseFrom(String input);
}