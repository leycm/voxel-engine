package sync.voxel.engine.api.util.namespace;

import org.bukkit.Material;
import sync.voxel.engine.api.util.identifier.VoxIdentifiable;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class VoxNameSpace implements VoxIdentifiable {

    private static final List<Material> SOLID_BLOCKS;

    static {
        SOLID_BLOCKS = Arrays.stream(Material.values())
                .filter(Material::isBlock)
                .filter(Material::isSolid)
                .filter(mat -> !mat.isLegacy())
                .collect(Collectors.toList());
    }

    private final String namespace;
    private final String displayName;
    private final VoxIdentifier identifier;

    public static @NotNull VoxNameSpace of(@NotNull String namespaceKey) {
        return of(namespaceKey, null);
    }

    public static @NotNull VoxNameSpace of(@NotNull String namespace, String displayName) {
        String display = displayName != null ? displayName : generateDisplayName(namespace);
        return new VoxNameSpace(namespace, display);
    }

    private VoxNameSpace(@NotNull String namespace, @NotNull String displayName) {
        this.namespace = namespace;
        this.displayName = displayName;
        this.identifier = VoxIdentifier.of(namespace, "namespace");
    }

    @Override
    public VoxIdentifier identifier() {
        return identifier;
    }

    public String displayName() {
        return displayName;
    }

    public @NotNull Material resolveMaterial() {
        if (namespace.equalsIgnoreCase("minecraft")) {
            return Material.GRASS_BLOCK;
        }
        int index = Math.abs(namespace.hashCode()) % SOLID_BLOCKS.size();
        return SOLID_BLOCKS.get(index);
    }

    private static @NotNull String generateDisplayName(@NotNull String raw) {
        String[] parts = raw.split("[_\\-]+");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            sb.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                sb.append(part.substring(1));
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoxNameSpace that)) return false;
        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "NameSpace{" +
                "identifier=" + identifier +
                ", displayName=\"" + displayName + '"' +
                ", material=" + resolveMaterial() +
                '}';
    }

}
