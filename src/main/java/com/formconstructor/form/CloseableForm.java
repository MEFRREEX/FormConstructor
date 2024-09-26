package com.formconstructor.form;

import com.formconstructor.form.handler.CloseHandler;
import lombok.Getter;

@Getter
public abstract class CloseableForm extends Form {

    public CloseableForm(FormType type) {
        super(type);
    }

    private transient CloseHandler closeHandler;

    /**
     * Set the form close handler
     * @param closeHandler CloseHandler
     * @return CloseableForm
     */
    public CloseableForm setCloseHandler(CloseHandler closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }
}