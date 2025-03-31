package com.formconstructor.form.element;

/**
 * Interface for simple form elements that don't process user input.
 */
public interface ElementSimple {

    /**
     * Gets the type of this form element.
     *
     * @return The element type
     */
    ElementType getType();

    /**
     * Gets the name (text) of this element.
     *
     * @return The element name
     */
    String getName();
}