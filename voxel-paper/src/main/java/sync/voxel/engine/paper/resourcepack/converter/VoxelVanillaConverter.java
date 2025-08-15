/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.resourcepack.converter;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.paper.VoxelPaperEngine;
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
        //Iterable<Tag<Material>> blockTags = Bukkit.getTags("blocks", Material.class);
        Iterable<Tag<Material>> itemsTags = Bukkit.getTags("items", Material.class);

        //for (Tag<Material> block : blockTags) registerVanillaGroup(block); blocks ar with in, in items.
        for (Tag<Material> item : itemsTags) registerVanillaGroup(item);
    }

    private static void registerVanillaGroup(@NotNull Tag<Material> tag) {
        VoxMaterialBehaviorGroup group = new VoxMaterialBehaviorGroup(VoxIdentifier.represent(tag.getKey()));

        for (Material material : tag.getValues()) {
            VoxelBukkitMaterial voxelMaterial = (VoxelBukkitMaterial) VoxRegistries.MATERIAL.valueOf(VoxIdentifier.represent(material.getKey()));
            if (voxelMaterial != null) group.add(voxelMaterial);
        }

        try {
            VoxRegistries.MATERIAL_GROUPS.register(group);
        } catch (Exception e) {
            VoxelPaperEngine.LOGGER.error("Fail to convert group with error \"{}\"", e.getMessage());
        }
    }

}
