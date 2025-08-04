/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.registry;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A {@code VoxIdentifier} is used to uniquely identify resources in the VoxelSync engine.
 * It consists of a namespace, a key, and an optional type.
 * <p>
 * Format: {@code namespace:key}
 * <p>
 * All characters must be lowercase a-z, digits, dots, underscores or hyphens.
 */
public final class VoxIdentifier {

    private final String namespace;
    private final String key;
    private final String type;

    /**
     * Parses a string representation of a {@code VoxIdentifier}.
     * Expected format is {@code namespace:key} or {@code namespace:key:type}.
     *
     * @param namespacedKey the bukkit {@link org.bukkit.NamespacedKey}
     * @return the {@code VoxIdentifier} for the {@link org.bukkit.NamespacedKey}
     * @throws IllegalArgumentException if the format is invalid or contains illegal characters
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VoxIdentifier represent(@NotNull NamespacedKey namespacedKey) {
        return VoxIdentifier.parse(namespacedKey.toString());
    }


    /**
     * Parses a string representation of a {@code VoxIdentifier}.
     * Expected format is {@code namespace:key} or {@code namespace:key:type}.
     *
     * @param input the input string
     * @return the parsed {@code VoxIdentifier}
     * @throws IllegalArgumentException if the format is invalid or contains illegal characters
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VoxIdentifier parse(String input) {
        return VoxIdentifier.parse(input, ':');
    }

    /**
     * Parses a string representation of a {@code VoxIdentifier}.
     * Expected format is {@code namespace:key} or {@code namespace:key:type}.
     *
     * @param input the input string
     * @param separator the separator default ':'
     * @return the parsed {@code VoxIdentifier}
     * @throws IllegalArgumentException if the format is invalid or contains illegal characters
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull VoxIdentifier parse(String input, char separator) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input identifier string is null or empty.");
        }

        String[] parts = input.split(String.valueOf(separator));
        if (parts.length < 2 || parts.length > 3) {
            throw new IllegalArgumentException("Invalid identifier format: expected namespace:key or namespace:key:type");
        }

        String namespace = parts[0];
        String key = parts[1];
        String type = parts.length == 3 ? parts[2] : "none";

        return new VoxIdentifier(namespace, key, type);
    }

    /**
     * Creates a new identifier with a default type.
     *
     * @param namespace the namespace
     * @param key       the key
     * @return the identifier
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull VoxIdentifier of(String namespace, String key) {
        return new VoxIdentifier(namespace, key, "none");
    }

    /**
     * Creates a new identifier with the given type.
     *
     * @param namespace the namespace
     * @param key       the key
     * @param type      the type
     * @return the identifier
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    public static @NotNull VoxIdentifier of(String namespace, String key, String type) {
        validate(namespace, "namespace");
        validate(key, "key");
        validate(type, "type");

        return new VoxIdentifier(namespace, key, type);
    }

    private VoxIdentifier(String namespace, String key, String type) {
        this.namespace = namespace;
        this.key = key;
        this.type = type;
    }

    public String namespace() {
        return namespace;
    }

    public String key() {
        return key;
    }

    public String type() {
        return type;
    }

    /**
     * Converts the identifier to string using ":" as default separator.
     *
     * @return formatted identifier
     */
    @Override
    public String toString() {
        return toString(':', 1);
    }

    /**
     * Converts the identifier to string with custom separator.
     *
     * @param separator the separator character
     * @return formatted identifier
     */
    public String toString(char separator) {
        return toString(separator, 1);
    }

    /**
     * Converts the identifier to string with type control.
     *
     * @param separator the separator character
     * @param type      0 = key only, 1 = namespace:key, 2 = type+namespace:key
     * @return formatted identifier
     */
    public String toString(char separator, int type) {
        return switch (type) {
            case 2 -> this.type + separator + namespace + separator + key;
            case 1 -> namespace + separator + key;
            case 0 -> key;
            default -> toString(separator, 1);
        };
    }

    /**
     * Validates a string to ensure all characters are allowed.
     *
     * @param value the string to validate
     * @param name  name of the field (for an error message)
     */
    private static void validate(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be null or empty");
        }

        for (char c : value.toCharArray()) {
            if (isInvalidChar(c)) {
                throw new IllegalArgumentException(name + " contains invalid character: '" + c + "'");
            }
        }
    }

    /**
     * Returns true if character is not valid.
     */
    private static boolean isInvalidChar(char c) {
        return (c < 'a' || c > 'z') &&
                (c < '0' || c > '9') &&
                c != '.' && c != '_' && c != '-';
    }

    /**
     * Checks equality based on namespace and key.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoxIdentifier that)) return false;

        return namespace.equals(that.namespace()) &&
                key.equals(that.key()) &&
                type.equals(that.type());

    }

    /**
     * Hash code based on namespace and key.
     */
    @Override
    public int hashCode() {
        return 31 * namespace.hashCode() + key.hashCode();
    }

}
