package sync.voxel.engine.common.logger;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.common.util.AsciColorUtil;

import java.awt.*;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
@Getter
public class VoxelLogger {
    private final String prefix;
    private final String coloredPrefix;
    private VoxelLogLevel currentLevel = VoxelLogLevel.INFO;

    public VoxelLogger(String prefix) {
        this.prefix = prefix;
        this.coloredPrefix = "[ " +  AsciColorUtil.colorizePrefix(prefix,
                new Color(0xFF0241),
                new Color(0xFF0241),
                new Color(0x003E83),
                new Color(0x003E83)
        ) + " ]:";

    }

    public void setLevel(VoxelLogLevel level) {
        this.currentLevel = level;
    }

    public void error(String msg, Object... args) {
        log(VoxelLogLevel.ERROR, msg, args);
    }

    public void warn(String msg, Object... args) {
        log(VoxelLogLevel.WARN, msg, args);
    }

    public void info(String msg, Object... args) {
        log(VoxelLogLevel.INFO, msg, args);
    }

    public void debug(String msg, Object... args) {
        log(VoxelLogLevel.DEBUG, msg, args);
    }

    private void log(@NotNull VoxelLogLevel level, String msg, Object... args) {
        if (level.getLevel() > currentLevel.getLevel()) return;

        String formattedMsg = formatMessage(msg, args);
        String reset = "\u001B[0m";
        String moveToStart = "\u001B[0G";
        String fullMsg = moveToStart + coloredPrefix + " [" + level.name() + "] " + reset + formattedMsg + "\n";

        try {
            new FileOutputStream(FileDescriptor.out).write(fullMsg.getBytes());
        } catch (Exception e) {}
    }

    private String formatMessage(String msg, Object... args) {
        if (args == null || args.length == 0) {
            return msg;
        }

        Throwable throwable = null;
        if (args[args.length - 1] instanceof Throwable) {
            throwable = (Throwable) args[args.length - 1];
            args = removeLastElement(args);
        }

        String formattedMsg = msg;
        for (Object arg : args) {
            formattedMsg = formattedMsg.replaceFirst("\\{\\}", arg != null ? arg.toString() : "null");
        }

        if (throwable != null) {
            formattedMsg += "\n" + getStackTraceAsString(throwable);
        }

        return formattedMsg;
    }

    private Object @NotNull [] removeLastElement(Object @NotNull [] array) {
        Object[] newArray = new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, newArray.length);
        return newArray;
    }

    private @NotNull String getStackTraceAsString(@NotNull Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\t").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
