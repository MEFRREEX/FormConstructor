package com.formconstructor.form;

import com.formconstructor.form.handler.ModalFormHandler;
import com.formconstructor.form.response.ModalFormResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

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

    public ModalForm() {
        this("");
    }

    public ModalForm(String title) {
        this(title, "");
    }

    public ModalForm(String title, String content) {
        super(FormType.MODAL);
        this.title = title;
        this.content = content;
    }

    public ModalForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public ModalForm setContent(String content) {
        this.content = content;
        return this;
    }

    public ModalForm addContent(String content) {
        this.content += content;
        return this;
    }

    public ModalForm setPositiveButton(String text) {
        this.positiveButton = text;
        return this;
    }

    public ModalForm setNegativeButton(String text) {
        this.negativeButton = text;
        return this;
    }

    public ModalForm setHandler(ModalFormHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void setResponse(String data) {
        if (!data.equals("null") && handler != null) {
            this.response = new ModalFormResponse(handler, data);
        }
    }
}