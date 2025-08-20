/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.paper.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * Utility class for analyzing stack traces and determining callers of methods.
 * Provides functionality to check if the current method was called by specific classes or packages.
 */
public class StackTraceCallerUtil {

    private static final int CURRENT_METHOD_INDEX = 0;
    private static final int CALLER_UTIL_INDEX = 1;
    private static final int DIRECT_CALLER_INDEX = 2;

    /**
     * Private constructor to prevent instantiation.
     */
    private StackTraceCallerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Gets the direct caller of the method that called this utility.
     *
     * @return Optional containing the StackTraceElement of the direct caller, or empty if not available
     */
    public static Optional<StackTraceElement> getDirectCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > DIRECT_CALLER_INDEX) {
            return Optional.of(stackTrace[DIRECT_CALLER_INDEX]);
        }

        return Optional.empty();
    }

    /**
     * Checks if the current method was called by the specified class.
     *
     * @param clazz the class to check against
     * @return true if the direct caller is from the specified class
     */
    public static boolean iamCalledBy(@NotNull Class<?> clazz) {
        return iamCalledBy(clazz.getName());
    }

    /**
     * Checks if the current method was called by the specified class name.
     *
     * @param className the fully qualified class name to check against
     * @return true if the direct caller is from the specified class
     */
    public static boolean iamCalledBy(String className) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > DIRECT_CALLER_INDEX) {
            return className.equals(stackTrace[DIRECT_CALLER_INDEX].getClassName());
        }

        return false;
    }

    /**
     * Checks if the current method was called by any of the specified classes.
     *
     * @param classes array of classes to check against
     * @return true if the direct caller is from any of the specified classes
     */
    public static boolean isCalledByAny(Class<?>... classes) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > DIRECT_CALLER_INDEX) {
            String callerClassName = stackTrace[DIRECT_CALLER_INDEX].getClassName();
            return Arrays.stream(classes)
                    .map(Class::getName)
                    .anyMatch(callerClassName::equals);
        }

        return false;
    }

    /**
     * Checks if the current method was called by a class from the specified package.
     *
     * @param packageClass a class from the target package
     * @return true if the direct caller is from the same package as the specified class
     */
    public static boolean iamCalledFromPackage(@NotNull Class<?> packageClass) {
        String packageName = packageClass.getPackage().getName();
        return iamCalledFromPackage(packageName);
    }

    /**
     * Checks if the current method was called by a class from the specified package.
     *
     * @param packageName the package name to check against
     * @return true if the direct caller is from the specified package
     */
    public static boolean iamCalledFromPackage(String packageName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > DIRECT_CALLER_INDEX) {
            String callerClassName = stackTrace[DIRECT_CALLER_INDEX].getClassName();
            return callerClassName.startsWith(packageName);
        }

        return false;
    }

    /**
     * Gets the full caller chain as a formatted string.
     *
     * @param maxDepth maximum number of callers to include in the chain
     * @return formatted caller chain string
     */
    public static @NotNull String getCallerChain(int maxDepth) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();

        int startIndex = Math.min(DIRECT_CALLER_INDEX, stackTrace.length);
        int endIndex = Math.min(startIndex + maxDepth, stackTrace.length);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                sb.append(" -> ");
            }
            StackTraceElement element = stackTrace[i];
            sb.append(element.getClassName())
                    .append(".")
                    .append(element.getMethodName())
                    .append("():")
                    .append(element.getLineNumber());
        }

        return sb.toString();
    }

    /**
     * Finds the first caller that matches the specified class in the call stack.
     *
     * @param clazz the class to search for
     * @return Optional containing the matching StackTraceElement, or empty if not found
     */
    public static Optional<StackTraceElement> findFirstCaller(@NotNull Class<?> clazz) {
        return findFirstCaller(clazz.getName());
    }

    /**
     * Finds the first caller that matches the specified class name in the call stack.
     *
     * @param className the class name to search for
     * @return Optional containing the matching StackTraceElement, or empty if not found
     */
    public static Optional<StackTraceElement> findFirstCaller(String className) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = DIRECT_CALLER_INDEX; i < stackTrace.length; i++) {
            if (className.equals(stackTrace[i].getClassName())) {
                return Optional.of(stackTrace[i]);
            }
        }

        return Optional.empty();
    }

    /**
     * Gets detailed information about the direct caller.
     *
     * @return formatted string with caller information
     */
    public static String getCallerInfo() {
        return getDirectCaller()
                .map(caller -> String.format("Caller: %s.%s() at line %d",
                        caller.getClassName(),
                        caller.getMethodName(),
                        caller.getLineNumber()))
                .orElse("Caller information not available");
    }

    /**
     * Prints the full stack trace to system out (for debugging purposes).
     */
    public static void printStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        System.out.println("Full Stack Trace:");
        for (int i = DIRECT_CALLER_INDEX; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            System.out.printf("%d: %s.%s() at line %d%n",
                    i - DIRECT_CALLER_INDEX,
                    element.getClassName(),
                    element.getMethodName(),
                    element.getLineNumber());
        }
    }
}