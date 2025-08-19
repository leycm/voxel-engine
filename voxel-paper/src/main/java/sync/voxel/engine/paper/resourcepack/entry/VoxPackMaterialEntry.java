/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.resourcepack.entry;

import lombok.Data;
import sync.voxel.engine.api.resourcepack.validator.VoxMaterialPresets;
import sync.voxel.engine.api.identifier.VoxIdentifier;
import sync.voxel.engine.api.identifier.VoxIdentifiable;

import java.io.File;
import java.util.Map;

@Data
public class VoxPackMaterialEntry implements VoxIdentifiable {

    private VoxIdentifier identifier;
    private VoxMaterialPresets preset;

    private Map<String, File> appearance;

    private Map<String, Object> settings;
    private Map<String, Object> potion_effects;
    private Map<String, Object> attributes;

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }

}

