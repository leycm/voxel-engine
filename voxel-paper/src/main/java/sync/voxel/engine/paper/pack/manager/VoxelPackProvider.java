package sync.voxel.engine.paper.pack.manager;

import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.VoxelEngine;
import sync.voxel.engine.paper.pack.VoxelPack;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class VoxelPackProvider {
    private static final String RESOURCE_BASE = "voxel/pack";
    private static final Path TARGET_PACK = Paths.get("plugins/voxel/pack");
    private static final Path TARGET_REGISTRY = TARGET_PACK.resolve("registry");

    public void provideDefaultPack() {
        if (!Files.exists(TARGET_REGISTRY)) {
            VoxelEngine.getLogger().info("\"registry/\" folder not found extracting default pack from JAR...");
            boolean success = extractResourcePackFromJar();
            if (!success) VoxelEngine.getLogger().error("Failed to extract resource pack from JAR.");
        } else {
            VoxelEngine.getLogger().debug("registry/ folder found skipping default pack copy.");
        }
    }

    /**
     * Extracts the resource pack from the JAR (inside resources/voxel/pack) to plugins/voxel/pack.
     *
     * @return true if successful, false otherwise
     */
    public boolean extractResourcePackFromJar() {
        try {
            URL resource = VoxelPack.class.getClassLoader().getResource(RESOURCE_BASE);
            if (resource == null) {
                throw new IllegalStateException("Cannot find resource folder inside JAR: " + RESOURCE_BASE);
            }

            if (resource.getProtocol().equals("jar")) {
                extractFromJar(resource);
            } else {
                throw new UnsupportedOperationException("Unsupported protocol: " + resource.getProtocol());
            }

            VoxelEngine.getLogger().info("Resource pack extracted to plugins/voxel/pack.");
            return true;

        } catch (Exception e) {
            VoxelEngine.getLogger().error("Extraction failed: {}", e.getMessage(), e);
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
