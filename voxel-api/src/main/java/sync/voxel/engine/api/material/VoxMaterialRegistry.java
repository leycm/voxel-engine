package sync.voxel.engine.api.material;

import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.registry.VoxIdentifier;
import sync.voxel.engine.api.registry.VoxRegistry;

public class VoxMaterialRegistry extends VoxRegistry<VoxMaterial> {

    @ApiStatus.Internal
    public VoxMaterialRegistry() {}

    public VoxMaterial valueOf(@NotNull Material vaMaterial) {
        return valueOf(VoxIdentifier.represent(vaMaterial.getKey()));
    }
}
