package com.formconstructor.form.element;

/**
 * Interface for custom form elements that can process user input.
 */
public interface ElementCustom {

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

    /**
     * Processes and validates the user input for this element.
     *
     * @param data The raw input data from the form submission
     * @return True if the input was valid and processed successfully, false otherwise
     */
    boolean respond(Object data);
}