/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.resourcepack.builder;

import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.paper.VoxelPaperEngine;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Utility class for building and managing the voxel resource pack.
 */
public class VoxelResourcePackBuilder {

    private static final String RESOURCE_BASE = "voxel/pack";
    private static final Path TARGET_PACK = Paths.get("plugins/voxel/pack");
    private static final Path TARGET_REGISTRY = TARGET_PACK.resolve("registry");

    /**
     * Builds the resource pack by ensuring required files are in place.
     */
    public static void buildPack(String reason) {
        if (!Files.exists(TARGET_REGISTRY)) {
            VoxelPaperEngine.LOGGER.info("\"registry/\" folder not found extracting default pack from JAR...");
            boolean success = extractResourcePackFromJar();
            if (!success) VoxelPaperEngine.LOGGER.error("Failed to extract resource pack from JAR.");
        } else {
            VoxelPaperEngine.LOGGER.debug("registry/ folder found skipping default pack copy.");
        }
    }

    /**
     * Extracts the resource pack from the JAR (inside resources/voxel/pack) to plugins/voxel/pack.
     *
     * @return true if successful, false otherwise
     */
    public static boolean extractResourcePackFromJar() {
        try {
            URL resource = VoxelResourcePackBuilder.class.getClassLoader().getResource(RESOURCE_BASE);
            if (resource == null) {
                throw new IllegalStateException("Cannot find resource folder inside JAR: " + RESOURCE_BASE);
            }

            if (resource.getProtocol().equals("jar")) {
                extractFromJar(resource);
            } else {
                throw new UnsupportedOperationException("Unsupported protocol: " + resource.getProtocol());
            }

            VoxelPaperEngine.LOGGER.info("Resource pack extracted to plugins/voxel/pack.");
            return true;

        } catch (Exception e) {
            VoxelPaperEngine.LOGGER.error("Extraction failed: {}\n{}", e.getMessage(), e);
            return false;
        }
    }

    private static void extractFromJar(@NotNull URL resource) throws IOException {
        String pathInJar = RESOURCE_BASE + "/";
        String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));

        try (JarFile jarFile = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {

            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {

                JarEntry entry = entries.nextElement();
                if (!entry.getName().startsWith(pathInJar)) continue;

                String relativePath = entry.getName().substring(pathInJar.length());
                Path outPath = TARGET_PACK.resolve(relativePath);

                if (!entry.isDirectory()) {
                    try (InputStream in = jarFile.getInputStream(entry)) {
                        Files.createDirectories(outPath.getParent());
                        Files.copy(in, outPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } else Files.createDirectories(outPath);
            }
        }

    }
}
