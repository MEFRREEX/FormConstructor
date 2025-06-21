package com.formconstructor.form.element.custom;

import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.ElementTooltip;
import com.formconstructor.form.element.ElementType;
import com.formconstructor.form.element.custom.validator.ValidationField;
import com.formconstructor.form.element.custom.validator.Validator;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Input extends ElementIdentifiable implements ElementCustom, ElementTooltip, ValidationField {

    private String placeholder;

    @SerializedName("default")
    private String defaultValue;

    private transient String value;

    private transient boolean trim;

    private transient List<Validator> validators;

    private String tooltip = "";

    public Input() {
        this("");
    }

    public Input(String name) {
        this(name, "");
    }

    public Input(String name, String placeholder) {
        this(name, placeholder, "");
    }

    public Input(String name, String placeholder, String defaultValue) {
        this(name, placeholder, defaultValue, false, new ArrayList<>());
    }

    private Input(String name, String placeholder, String defaultValue, boolean trim, List<Validator> validators) {
        super(name, ElementType.INPUT);
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.trim = trim;
        this.validators = validators;
    }

    @Override
    public Input setName(String name) {
        return (Input) super.setName(name);
    }

    public Input setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Input setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Input setTrim(boolean trim) {
        this.trim = trim;
        return this;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public Input setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void validate() {
        validators.forEach(validator -> validator.validate(value));
    }

    @Override
    public boolean isValidated() {
        return validators.stream().allMatch(Validator::isValidated);
    }

    @Override
    public Input addValidator(Validator validator) {
        validators.add(validator);
        return this;
    }

    public Input setValidators(List<Validator> validators) {
        this.validators = validators;
        return this;
    }

    public Input addValidators(Validator... validator) {
        this.validators.addAll(Arrays.asList(validator));
        return this;
    }

    @Override
    public boolean respond(Object data) {
        this.value = trim ? ((String) data).trim() : (String) data;
        this.validate();
        return true;
    }
}