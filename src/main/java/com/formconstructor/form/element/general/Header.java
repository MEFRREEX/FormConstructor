package com.formconstructor.form.element.general;

import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.ElementSimple;
import com.formconstructor.form.element.ElementType;

public class Header extends ElementIdentifiable implements ElementSimple, ElementCustom {

    public Header() {
        this("");
    }

    public Header(String text) {
        super(text, ElementType.HEADER);
    }

    @Override
    public boolean respond(Object data) {
        return true;
    }
}