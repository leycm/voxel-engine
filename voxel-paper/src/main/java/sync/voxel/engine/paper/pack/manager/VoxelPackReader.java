package sync.voxel.engine.paper.pack.manager;

import sync.voxel.engine.api.VoxelEngine;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VoxelPackReader {
    private static final String RESOURCE_BASE = "voxel/pack";
    private static final Path TARGET_PACK = Paths.get("plugins/voxel/pack");
    private static final Path TARGET_REGISTRY = TARGET_PACK.resolve("registry");

    public void readDeepPack() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(TARGET_REGISTRY, Files::isDirectory)) {
            for (Path dir : stream) readSubPack(dir);
        } catch (IOException e) {
            VoxelEngine.getLogger().error("Try to read a folder", e);
        }
    }

    private void readSubPack(Path dir) {

    }

    private void readMaterialsRegistry(Path dir) {

    }

}
