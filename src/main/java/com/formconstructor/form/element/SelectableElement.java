package com.formconstructor.form.element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectableElement {

    private final String name;
    private final Object value;
    private int index = -1;

    public SelectableElement(String name) {
        this(name, null);
    }

    public SelectableElement(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public <T> T getValue(Class<T> clazz) {
        return clazz.cast(value);
    }
}