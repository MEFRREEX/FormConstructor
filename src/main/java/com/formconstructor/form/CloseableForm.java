package com.formconstructor.form;

import com.formconstructor.form.handler.CloseFormHandler;
import lombok.Getter;

@Getter
public abstract class CloseableForm extends Form {

    public CloseableForm(FormType type) {
        super(type);
    }

    private transient CloseFormHandler closeHandler;

    /**
     * Set the form close handler
     * @param closeHandler CloseHandler
     * @return CloseableForm
     */
    public CloseableForm setCloseHandler(CloseFormHandler closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }
}