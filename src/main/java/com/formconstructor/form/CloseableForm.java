package com.formconstructor.form;

import com.formconstructor.form.handler.CloseFormHandler;
import com.formconstructor.form.handler.CloseReasonFormHandler;
import lombok.Getter;

/**
 * Abstract base class for forms that can handle close events.
 */
@Getter
public abstract class CloseableForm extends Form {

    private transient CloseReasonFormHandler closeHandler;

    /**
     * Creates a new closeable form of specified type.
     *
     * @param type The type of form to create
     */
    public CloseableForm(FormType type) {
        super(type);
    }

    /**
     * Sets a handler without a reason handling for the form close event.
     *
     * @param closeHandler The handler to be called when form is closed
     * @return This form instance for method chaining
     */
    public CloseableForm setCloseHandler(CloseFormHandler closeHandler) {
        return this.setCloseHandler(CloseReasonFormHandler.withoutReason(closeHandler));
    }

    /**
     * Sets a handler with a reason for the form close event.
     *
     * @param closeHandler The handler to be called when form is closed
     * @return This form instance for method chaining
     */
    public CloseableForm setCloseHandler(CloseReasonFormHandler closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }
}