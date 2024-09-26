package com.formconstructor.form.element.custom.validator;

import lombok.Getter;

@Getter
public abstract class Validator {

    private final String name;
    private boolean validated;

    public Validator(String name) {
        this.name = name;
    }

    protected void setValidated(boolean validated) {
        this.validated = validated;
    }

    public abstract void validate(String input);
}
