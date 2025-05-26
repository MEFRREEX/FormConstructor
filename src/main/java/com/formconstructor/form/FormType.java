package com.formconstructor.form;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a types of forms.
 */
public enum FormType {
    /**
     * Simple form with buttons.
     */
    @SerializedName("form") SIMPLE,
    /**
     * Modal form with two buttons.
     */
    @SerializedName("modal") MODAL,
    /**
     * Custom form with inputs, toggles, dropdowns etc.
     */
    @SerializedName("custom_form") CUSTOM
}