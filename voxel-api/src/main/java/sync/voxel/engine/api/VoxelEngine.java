/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api;

import org.slf4j.Logger;

import sync.voxel.engine.api.identifier.VoxIdentifier;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;


public final class VoxelEngine {
    private static VoxEngine engine = null;

    // ====== CLASS PROVIDER ======

    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = """
                The Voxel API isn't loaded yet!
                This could be because:
                 a) the Voxel plugin is not installed or it failed to enable
                 b) the plugin in the stacktrace does not declare a dependency on Voxel
                 c) the plugin in the stacktrace is retrieving the API before the plugin 'enable' phase
                 (call the #get method in onEnable, not the constructor!)
                 d) the plugin in the stacktrace is incorrectly 'shading' the Voxel API into its jar
                """;

        NotLoadedException() {
            super(MESSAGE);
        }
    }

    public static @NonNull VoxEngine getEngine() {
        VoxEngine engine = VoxelEngine.engine;
        if (engine == null) throw new NotLoadedException();
        return engine;
    }

    @ApiStatus.Internal
    public static void register(VoxEngine engine) {
        VoxelEngine.engine = engine;
    }

    @ApiStatus.Internal
    public static void unregister() {
        VoxelEngine.engine = null;
    }

    @ApiStatus.Internal
    private VoxelEngine() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    // ====== VOXEL ENGINE TUNNEL ======

    public static String translate(String langCode, VoxIdentifier identifier) {
        return engine.translate(langCode, identifier);
    }

    public static Logger getLogger() {
        return engine.logger();
    }

}
