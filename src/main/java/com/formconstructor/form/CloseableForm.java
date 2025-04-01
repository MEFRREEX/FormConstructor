package com.formconstructor.form;

import com.formconstructor.form.handler.CloseFormHandler;
import lombok.Getter;

/**
 * Abstract base class for forms that can handle close events.
 */
@Getter
public abstract class CloseableForm extends Form {

    private transient CloseFormHandler closeHandler;

    /**
     * Creates a new closeable form of specified type.
     *
     * @param type The type of form to create
     */
    public CloseableForm(FormType type) {
        super(type);
    }

    /**
     * Sets the handler for form close events.
     *
     * @param closeHandler The handler to be called when form is closed
     * @return This form instance for method chaining
     */
    public CloseableForm setCloseHandler(CloseFormHandler closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }
}