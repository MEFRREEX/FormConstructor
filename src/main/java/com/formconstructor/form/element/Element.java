package com.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;

/**
 * Abstract base class for all form elements.
 */
public abstract class Element {

    @SerializedName("text")
    private String name;

    private final ElementType type;

    /**
     * Creates a new form element.
     *
     * @param name The display name of the element
     * @param type The element type
     */
    public Element(String name, ElementType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the element type.
     *
     * @return The element type
     */
    public ElementType getType() {
        return type;
    }

    /**
     * Gets the element display name.
     *
     * @return The element name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the element display name.
     *
     * @param name The new display name
     * @return this element instance for chaining
     */
    public Element setName(String name) {
        this.name = name;
        return this;
    }
}