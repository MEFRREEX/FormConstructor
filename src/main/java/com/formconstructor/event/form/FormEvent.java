package com.formconstructor.event.form;

import cn.nukkit.event.Event;
import com.formconstructor.form.Form;
import lombok.Getter;

@Getter
public abstract class FormEvent extends Event {

    private final Form form;

    public FormEvent(Form form) {
        this.form = form;
    }
}
