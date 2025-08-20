/**
 * VOXEL-LICENSE NOTICE
 * <br><br>
 * This software is part of VoxelSync under the Voxel Public License. <br>
 * Source at: <a href="https://github.com/voxelsync/voxel/blob/main/LICENSE">GITHUB</a>
 * <br><br>
 * Copyright (c) Ley <cm.ley.cm@gmail.com> <br>
 * Copyright (c) contributors
 */
package sync.voxel.engine.api.identifier;

import org.bukkit.NamespacedKey;
import sync.voxel.engine.api.VoxelEngine;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A {@code VoxIdentifier} uniquely identifies resources within the VoxelSync engine.
 * <p>
 * Identifiers follow the format:
 * <pre>
 * namespace:key
 * namespace:key:type
 * </pre>
 * Characters are restricted to lowercase {@code a-z}, digits {@code 0-9}, dots ({@code .}),
 * underscores ({@code _}), and hyphens ({@code -}).
 * <p>
 * An optional {@code type} may be included. If omitted, it defaults to {@code "none"}.
 *
 * @since 1.0.1
 */
public final class VoxIdentifier {

    private final String namespace;
    private final String key;
    private final String type;

    /**
     * Creates a {@code VoxIdentifier} from a Bukkit {@link NamespacedKey}.
     *
     * @param namespacedKey the Bukkit namespaced key
     * @return the corresponding {@code VoxIdentifier}
     * @throws IllegalArgumentException if the key contains invalid characters
     *
     * @since 1.0.1
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VoxIdentifier represent(@NotNull NamespacedKey namespacedKey) throws IllegalArgumentException {
        return VoxIdentifier.parse(namespacedKey.toString());
    }

    /**
     * Parses a string into a {@code VoxIdentifier}.
     * <p>
     * Expected format:
     * <ul>
     *   <li>{@code namespace:key}</li>
     *   <li>{@code namespace:key:type}</li>
     * </ul>
     *
     * @param input the identifier string
     * @return the parsed {@code VoxIdentifier}
     * @throws IllegalArgumentException if the input is {@code null}, blank, malformed,
     *                                  or contains illegal characters
     *
     * @since 1.0.1
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VoxIdentifier parse(String input) throws IllegalArgumentException {
        return VoxIdentifier.parse(input, ':');
    }

    /**
     * Parses a string into a {@code VoxIdentifier} using a custom separator.
     * <p>
     * Expected format:
     * <ul>
     *   <li>{@code namespace{separator}key}</li>
     *   <li>{@code namespace{separator}key{separator}type}</li>
     * </ul>
     *
     * @param input     the identifier string
     * @param separator the separator character (commonly {@code ':'})
     * @return the parsed {@code VoxIdentifier}
     * @throws IllegalArgumentException if the input is {@code null}, blank, malformed,
     *                                  or contains illegal characters
     *
     * @since 1.0.1
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull VoxIdentifier parse(String input, char separator) throws IllegalArgumentException {
        if (input == null || input.isBlank())
            throw new IllegalArgumentException("Input identifier string is null or empty.");

        String[] parts = input.split(String.valueOf(separator));
        if (parts.length < 2 || parts.length > 3)
            throw new IllegalArgumentException("Invalid identifier format: expected namespace:key or namespace:key:type");

        String namespace = parts[0];
        String key = parts[1];
        String type = parts.length == 3 ? parts[2] : "none";

        return new VoxIdentifier(namespace, key, type);
    }

    /**
     * Creates a new {@code VoxIdentifier} with type set to {@code "none"}.
     *
     * @param namespace the namespace
     * @param key       the key
     * @return a new {@code VoxIdentifier}
     * @throws IllegalArgumentException if any parameter is {@code null}, blank,
     *                                  or contains illegal characters
     *
     * @since 1.0.1
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull VoxIdentifier of(String namespace, String key) throws IllegalArgumentException {
        return new VoxIdentifier(namespace, key, "none");
    }

    /**
     * Creates a new {@code VoxIdentifier} with the given type.
     *
     * @param namespace the namespace
     * @param key       the key
     * @param type      the type
     * @return a new {@code VoxIdentifier}
     * @throws IllegalArgumentException if any parameter is {@code null}, blank,
     *                                  or contains illegal characters
     *
     * @since 1.0.1
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    public static @NotNull VoxIdentifier of(String namespace, String key, String type) throws IllegalArgumentException {
        validate(namespace, "namespace");
        validate(key, "key");
        validate(type, "type");

        return new VoxIdentifier(namespace, key, type);
    }

    /**
     * Private constructor for {@code VoxIdentifier}.
     *
     * @param namespace the namespace
     * @param key       the key
     * @param type      the type (or {@code "none"} if unspecified)
     *
     * @since 1.0.1
     */
    private VoxIdentifier(String namespace, String key, String type) {
        this.namespace = namespace;
        this.key = key;
        this.type = type;
    }

    /**
     * Get the {@code namespace} of the VoxIdentifier
     * @return the namespace of this identifier
     *
     * @since 1.0.1
     */
    public String namespace() {
        return namespace;
    }

    /**
     * Get the {@code key} of the VoxIdentifier
     * @return the key of this identifier
     *
     * @since 1.0.1
     */
    public String key() {
        return key;
    }

    /**
     * Get the {@code type} of the VoxIdentifier
     * @return the type of this identifier, or {@code "none"} if not set
     *
     * @since 1.0.1
     */
    public String type() {
        return type;
    }

    /**
     * Translates this identifier into a localized string.
     *
     * @param langCode the language code (e.g. {@code "en_us"})
     * @return the translated string
     *
     * @since 1.0.1
     */
    public String translate(String langCode) {
        return VoxelEngine.translate(langCode, this);
    }

    /**
     * Converts this identifier to a string using the default separator {@code ':'}.
     *
     * @return the formatted identifier
     *
     * @since 1.0.1
     */
    @Override
    public String toString() {
        return toString(':', 1);
    }

    /**
     * Converts this identifier to a string using a custom separator.
     *
     * @param separator the separator character
     * @return the formatted identifier
     *
     * @since 1.0.1
     */
    public String toString(char separator) {
        return toString(separator, 1);
    }

    /**
     * Converts this identifier to a string with full control over format.
     *
     * @param separator the separator character
     * @param type      output format:
     *                  <ul>
     *                      <li>{@code 0}: {@code key}</li>
     *                      <li>{@code 1}: {@code namespace{separator}key}</li>
     *                      <li>{@code 2}: {@code type{separator}namespace{separator}key}</li>
     *                      <li>{@code 3}: localized translation (English fallback)</li>
     *                  </ul>
     * @return the formatted identifier
     *
     * @since 1.0.1
     */
    public String toString(char separator, int type) {
        return switch (type) {
            case 3 -> translate("en" + separator + "us");
            case 2 -> this.type + separator + namespace + separator + key;
            case 1 -> namespace + separator + key;
            case 0 -> key;
            default -> toString(separator, 1);
        };
    }

    /**
     * Validates a string component of the identifier.
     *
     * @param value the string to validate
     * @param name  the field name for error reporting
     * @throws IllegalArgumentException if the string is {@code null}, blank,
     *                                  or contains illegal characters
     *
     * @since 1.0.1
     */
    private static void validate(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be null or empty");
        }
        for (char c : value.toCharArray()) {
            if (!isValidChar(c)) {
                throw new IllegalArgumentException(name + " contains invalid character: '" + c + "'");
            }
        }
    }

    /**
     * Checks whether a character is valid in an identifier.
     *
     * @param c the character to check
     * @return {@code true} if valid; otherwise {@code false}
     *
     * @since 1.0.1
     */
    private static boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= '0' && c <= '9') ||
                c == '.' || c == '_' || c == '-';
    }

    /**
     * Compares this identifier with another for equality.
     * Equality is based on {@code namespace}, {@code key}, and {@code type}.
     *
     * @param o the other object
     * @return {@code true} if equal, {@code false} otherwise
     *
     * @since 1.0.1
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
     * Computes the hash code of this identifier.
     * Based on {@code namespace} and {@code key}.
     *
     * @return the hash code
     *
     * @since 1.0.1
     */
    @Override
    public int hashCode() {
        return 31 * namespace.hashCode() + key.hashCode();
    }
}
