package sync.voxel.engine.api.pack.entry;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public interface VoxAppearanceEntry extends VoxRegistryEntry {

    Optional<File> getModel();

    File getLayer(String layer);

    Map<String, File> getLayers();

}
