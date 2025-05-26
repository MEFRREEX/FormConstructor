package com.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration of all supported form element types.
 */
public enum ElementType {
    /**
     * Clickable button element
     */
    @SerializedName("button") BUTTON,
    /**
     * Form section header element
     */
    @SerializedName("header") HEADER,
    /**
     * Text label element element
     */
    @SerializedName("label") LABEL,
    /**
     * Visual divider line element
     */
    @SerializedName("divider") DIVIDER,
    /**
     * Dropdown selection element
     */
    @SerializedName("dropdown") DROPDOWN,
    /**
     * Text input field
     */
    @SerializedName("input") INPUT,
    /**
     * Numeric slider element
     */
    @SerializedName("slider") SLIDER,
    /**
     * Stepped slider with predefined values element
     */
    @SerializedName("step_slider") STEP_SLIDER,
    /**
     * Toggle element
     */
    @SerializedName("toggle") TOGGLE;
}