package com.formconstructor.form.element.custom;

import com.formconstructor.form.element.ElementType;
import com.formconstructor.form.element.FormElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CustomElement extends FormElement {

    private final ElementType type;
    private transient String elementId;
    private transient int elementIndex = -1;

    public CustomElement(String name, ElementType type) {
        super(name);
        this.type = type;
    }

    public abstract boolean respond(Object data);
}