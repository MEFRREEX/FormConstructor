package com.formconstructor.form.element;

public interface ElementCustom {

    ElementType getType();

    String getName();

    boolean respond(Object data);
}