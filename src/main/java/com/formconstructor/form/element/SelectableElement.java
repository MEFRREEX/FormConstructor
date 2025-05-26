package com.formconstructor.form.element;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a selectable option in form elements like dropdowns or sliders.
 */
@Getter
@Setter
public class SelectableElement {

    private final String name;
    private final Object value;
    private int index = -1;

    /**
     * Creates a selectable option with only a name.
     *
     * @param name The name of the option
     */
    public SelectableElement(String name) {
        this(name, null);
    }

    /**
     * Creates a selectable option with name and associated value.
     *
     * @param name The name of the option
     * @param value The associated value (can be null)
     */
    public SelectableElement(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the option value cast to specified type.
     *
     * @param <T> The target type for casting
     * @param clazz The class object of target type
     * @return The cast value
     * @throws ClassCastException If the value cannot be cast to specified type
     */
    public <T> T getValue(Class<T> clazz) {
        return clazz.cast(value);
    }
}