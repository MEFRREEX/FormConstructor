package com.formconstructor.event.form;

import com.formconstructor.form.Form;
import cn.nukkit.event.Event;
import lombok.Getter;

@Getter
public abstract class FormEvent extends Event {
    
    private final Form form;

    public FormEvent(Form form) {
        this.form = form;
    }
}
