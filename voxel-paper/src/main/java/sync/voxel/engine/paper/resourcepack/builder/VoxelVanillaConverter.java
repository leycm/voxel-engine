package sync.voxel.engine.paper.resourcepack.builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.paper.material.VoxMaterialBehaviorGroup;
import sync.voxel.engine.api.util.identifier.VoxIdentifier;
import sync.voxel.engine.api.registry.VoxRegistries;
import sync.voxel.engine.paper.material.VoxelBukkitMaterial;

public class VoxelVanillaConverter {

    public static void convertVanilla() {
        convertMaterials();
        convertMaterialGroups();
    }

    private static void convertMaterials() {
        for (Material material : Material.values()) {
            VoxRegistries.MATERIAL.register(new VoxelBukkitMaterial(material));
        }
    }

    private static void convertMaterialGroups() {
        Iterable<Tag<Material>> blockTags = Bukkit.getTags("blocks", Material.class);
        Iterable<Tag<Material>> itemsTags = Bukkit.getTags("items", Material.class);

        for (Tag<Material> block : blockTags) registerVanillaGroup(block);
        for (Tag<Material> item : itemsTags) registerVanillaGroup(item);
    }

    private static void registerVanillaGroup(@NotNull Tag<Material> tag) {
        VoxMaterialBehaviorGroup group = new VoxMaterialBehaviorGroup(VoxIdentifier.represent(tag.getKey()));

        for (Material material : tag.getValues()) {
            VoxelBukkitMaterial voxelMaterial = (VoxelBukkitMaterial) VoxRegistries.MATERIAL.valueOf(VoxIdentifier.represent(material.getKey()));
            if (voxelMaterial != null) {
                group.add(voxelMaterial);
            }
        }

        VoxRegistries.MATERIAL_GROUPS.register(group);
    }

}
