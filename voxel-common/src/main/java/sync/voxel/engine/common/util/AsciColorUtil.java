package sync.voxel.engine.common.util;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AsciColorUtil {

    private static final String RESET = "\u001B[0m";

    public static @NotNull String colorizePrefix(@NotNull String prefix, @NotNull Color @NotNull ... colors) {
        if (colors.length < 2)
            throw new IllegalArgumentException("At least 2 colors are required");

        StringBuilder sb = new StringBuilder();
        int length = prefix.length();
        int segments = colors.length - 1;

        for (int i = 0; i < length; i++) {
            double relativePos = (double) i / (length - 1);
            double scaledPos = relativePos * segments;
            int segment = Math.min((int) scaledPos, segments - 1);
            double localT = scaledPos - segment;

            Color start = colors[segment];
            Color end = colors[segment + 1];

            Color interpolated = interpolateColor(start, end, localT);
            sb.append(toAnsi(interpolated));
            sb.append(prefix.charAt(i));
        }

        sb.append(RESET);
        return sb.toString();
    }

    private static @NotNull Color interpolateColor(@NotNull Color start, @NotNull Color end, double t) {
        int r = (int) (start.getRed() + (end.getRed() - start.getRed()) * t);
        int g = (int) (start.getGreen() + (end.getGreen() - start.getGreen()) * t);
        int b = (int) (start.getBlue() + (end.getBlue() - start.getBlue()) * t);
        return new Color(r, g, b);
    }

    private static @NotNull String toAnsi(@NotNull Color color) {
        return String.format("\u001B[38;2;%d;%d;%dm", color.getRed(), color.getGreen(), color.getBlue());
    }
}
