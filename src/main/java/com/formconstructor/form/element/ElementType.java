package com.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;

public enum ElementType {
    @SerializedName("button") BUTTON,
    @SerializedName("header") HEADER,
    @SerializedName("label") LABEL,
    @SerializedName("divider") DIVIDER,
    @SerializedName("dropdown") DROPDOWN,
    @SerializedName("input") INPUT,
    @SerializedName("slider") SLIDER,
    @SerializedName("step_slider") STEP_SLIDER,
    @SerializedName("toggle") TOGGLE;
}