package sync.voxel.engine.paper.resourcepack.entry;

import lombok.Data;
import sync.voxel.engine.api.resourcepack.validator.VoxMaterialPresets;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.api.util.identifier.VoxIdentifiable;

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

