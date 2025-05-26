package com.formconstructor.form.element.custom;

import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.ElementType;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Slider extends ElementIdentifiable implements ElementCustom {

    @SerializedName("default")
    private float defaultValue;

    private float min;
    private float max;
    private float step;

    private transient float value = -1;

    public Slider() {
        this("");
    }

    public Slider(String name) {
        this(name, 0f, 100f);
    }

    public Slider(String name, float min, float max) {
        this(name, min, max, 1);
    }

    public Slider(String name, float min, float max, int step) {
        this(name, min, max, step, -1);
    }

    public Slider(String name, float min, float max, int step, float defaultValue) {
        super(name, ElementType.SLIDER);
        this.min = Math.max(min, 0f);
        this.max = Math.max(max, this.min);
        this.defaultValue = defaultValue;
        if (step > 0) {
            this.step = step;
        }
    }

    @Override
    public Slider setName(String name) {
        return (Slider) super.setName(name);
    }

    public Slider setMin(float min) {
        this.min = min;
        return this;
    }

    public Slider setMax(float max) {
        this.max = max;
        return this;
    }

    public Slider setStep(int step) {
        this.step = step;
        return this;
    }

    public Slider setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean respond(Object data) {
        this.value = ((Double) data).floatValue();
        return value >= min && value <= max;
    }
}