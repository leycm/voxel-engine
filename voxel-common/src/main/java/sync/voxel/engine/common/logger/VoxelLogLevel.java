package sync.voxel.engine.common.logger;

import lombok.Getter;

@Getter
public enum VoxelLogLevel {
    ERROR(1),
    WARN(2),
    INFO(3),
    DEBUG(4);

    private final int level;

    VoxelLogLevel(int level) {
        this.level = level;
    }

}
