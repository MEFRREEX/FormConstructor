package com.formconstructor.form;

import com.formconstructor.form.handler.ModalFormHandler;
import com.formconstructor.form.response.ModalFormResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Represents a modal with two buttons form that can be shown to players.
 */
@Getter
public class ModalForm extends CloseableForm {

    private String title;
    private String content;
    
    @SerializedName("button1") 
    private String positiveButton;
    
    @SerializedName("button2")
    private String negativeButton;
    
    private transient ModalFormHandler handler;
    private transient ModalFormResponse response;

    /**
     * Creates an empty modal form.
     */
    public ModalForm() {
        this("");
    }

    /**
     * Creates a modal form with title.
     *
     * @param title The form title
     */
    public ModalForm(String title) {
        this(title, "");
    }

    /**
     * Creates a modal form with title and content.
     *
     * @param title The form title
     * @param content The form content text
     */
    public ModalForm(String title, String content) {
        super(FormType.MODAL);
        this.title = title;
        this.content = content;
    }

    /**
     * Sets the form title.
     *
     * @param title The title text
     * @return This form instance for chaining
     */
    public ModalForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the form content text.
     *
     * @param content The content text
     * @return This form instance for chaining
     */
    public ModalForm setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Appends text to the form content.
     *
     * @param content The content text to append
     * @return This form instance for chaining
     */
    public ModalForm addContent(String content) {
        this.content += content;
        return this;
    }

    /**
     * Sets the positive button text.
     *
     * @param text The button text
     * @return This form instance for chaining
     */
    public ModalForm setPositiveButton(String text) {
        this.positiveButton = text;
        return this;
    }

    /**
     * Sets the negative button text.
     *
     * @param text The button text
     * @return This form instance for chaining
     */
    public ModalForm setNegativeButton(String text) {
        this.negativeButton = text;
        return this;
    }

    /**
     * Sets the form submission handler.
     *
     * @param handler The handler instance
     * @return This form instance for chaining
     */
    public ModalForm setHandler(ModalFormHandler handler) {
        this.handler = handler;
        return this;
    }

    /**
     * Processes the form response data.
     *
     * @param data The raw response data from client
     */
    @Override
    public void setResponse(String data) {
        if (!data.equals("null") && handler != null) {
            this.response = new ModalFormResponse(handler, data);
        }
    }
}