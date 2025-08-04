package sync.voxel.engine.api.material;

import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.group.VoxBehaviorGroup;
import sync.voxel.engine.api.registry.VoxIdentifier;

import java.util.HashMap;
import java.util.Map;

public class VoxMaterialBehaviorGroup implements VoxBehaviorGroup<VoxMaterial> {

    private final Map<VoxIdentifier, VoxMaterial> elements;
    private final VoxIdentifier identifier;

    public VoxMaterialBehaviorGroup(VoxIdentifier identifier) {
        this.identifier = identifier;
        this.elements = new HashMap<>();
    }

    @Override
    public void add(@NotNull VoxMaterial element) {
        if (!elements.containsKey(element.identifier())) elements.put(element.identifier(), element);
    }

    @Override
    public void remove(@NotNull VoxMaterial element) {
        elements.remove(element.identifier());
    }

    @Override
    public boolean contains(@NotNull VoxMaterial element) {
        return elements.containsKey(element.identifier());
    }

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }
}
