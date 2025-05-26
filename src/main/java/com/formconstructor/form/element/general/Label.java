package com.formconstructor.form.element.general;

import com.formconstructor.form.element.*;

public class Label extends ElementIdentifiable implements ElementSimple, ElementCustom {

    public Label() {
        this("");
    }

    public Label(String text) {
        super(text, ElementType.LABEL);
    }

    @Override
    public boolean respond(Object data) {
        return true;
    }
}