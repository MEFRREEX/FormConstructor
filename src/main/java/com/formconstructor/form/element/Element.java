package com.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;

public abstract class Element {

    @SerializedName("text")
    private String name;

    private final ElementType type;

    public Element(String name, ElementType type) {
        this.name = name;
        this.type = type;
    }

    public ElementType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Element setName(String name) {
        this.name = name;
        return this;
    }
}