package com.formconstructor.form;

import com.google.gson.annotations.SerializedName;

public enum FormType {
    @SerializedName("form") SIMPLE,
    @SerializedName("modal") MODAL,
    @SerializedName("custom_form") CUSTOM;
}
