package com.formconstructor.form;

import cn.nukkit.Player;
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
        this(title, content, "", "");
    }

    public ModalForm(String title, String content, String positiveButton, String negativeButton) {
        this(title, content, positiveButton, negativeButton, null);
    }

    public ModalForm(String title, String content, String positiveButton, String negativeButton, ModalFormHandler handler) {
        super(FormType.MODAL);
        this.title = title;
        this.content = content;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.handler = handler;
    }

    /**
     * Set form title
     * @param title Text
     * @return ModalForm
     */
    public ModalForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set form content
     * @param content Text
     * @return ModalForm
     */
    public ModalForm setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Add form content
     * @param content Text
     * @return ModalForm
     */
    public ModalForm addContent(String content) {
        this.content += content;
        return this;
    }

    /**
     * Set the first button in the form
     * @param text Button text
     * @return ModalForm
     */
    public ModalForm setPositiveButton(String text) {
        this.positiveButton = text;
        return this;
    }

    /**
     * Set the second button in the form
     * @param text Button text
     * @return ModalForm
     */
    public ModalForm setNegativeButton(String text) {
        this.negativeButton = text;
        return this;
    }

    /**
     * Set form handler
     * @param handler ModalFormHandler
     * @return ModalForm
     */
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

    public void send(Player player, ModalFormHandler handler) {
        this.setHandler(handler);
        this.send(player);
    }
}