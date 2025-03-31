package com.formconstructor.form.element.general;

import com.formconstructor.form.element.*;

public class Divider extends ElementIdentifiable implements ElementSimple, ElementCustom {

    public Divider() {
        super("", ElementType.DIVIDER);
    }

    @Override
    public Element setName(String name) {
        throw new UnsupportedOperationException("Divider element cannot have a name");
    }

    @Override
    public boolean respond(Object data) {
        return true;
    }
}