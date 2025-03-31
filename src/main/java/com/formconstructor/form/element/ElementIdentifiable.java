package com.formconstructor.form.element;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for identifiable form elements (e.g. custom elements).
 */
@Getter
@Setter
public abstract class ElementIdentifiable extends Element {

    private transient String elementId;

    /**
     * Creates a new identifiable form element.
     *
     * @param name The display name of the element
     * @param type The type of form element
     */
    public ElementIdentifiable(String name, ElementType type) {
        super(name, type);
    }
}