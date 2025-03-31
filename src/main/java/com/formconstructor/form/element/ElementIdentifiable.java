package com.formconstructor.form.element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ElementIdentifiable extends Element {

    private transient String elementId;

    public ElementIdentifiable(String name, ElementType type) {
        super(name, type);
    }
}