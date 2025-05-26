package com.formconstructor.form.element.custom;

import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.ElementType;
import com.google.gson.annotations.SerializedName;

public class Toggle extends ElementIdentifiable implements ElementCustom {

    @SerializedName("default") 
    private final boolean defaultValue;

    private transient boolean value;

    public Toggle() {
        this("");
    }

    public Toggle(String name) {
        this(name, false);
    }

    public Toggle(String name, boolean defaultValue) {
        super(name, ElementType.TOGGLE);
        this.defaultValue = defaultValue;
    }

    @Override
    public Toggle setName(String name) {
        return (Toggle) super.setName(name);
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean respond(Object data) {
        this.value = (boolean) data;
        return true;
    }
}