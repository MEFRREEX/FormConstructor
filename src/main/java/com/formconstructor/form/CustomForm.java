package com.formconstructor.form;

import cn.nukkit.Player;
import com.formconstructor.form.element.custom.CustomElement;
import com.formconstructor.form.element.custom.Label;
import com.formconstructor.form.element.custom.validator.ValidationField;
import com.formconstructor.form.handler.CustomFormHandler;
import com.formconstructor.form.response.CustomFormResponse;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomForm extends CloseableForm {

    private String title;

    @SerializedName("content")
    private final List<CustomElement> elements = new ArrayList<>();

    @Getter
    private transient boolean validated = true;

    private transient CustomFormHandler handler;
    private transient CustomFormResponse response;

    public CustomForm() {
        this("", null);
    }

    public CustomForm(String title) {
        this(title, null);
    }

    public CustomForm(CustomFormHandler handler) {
        this("", handler);
    }

    public CustomForm(String title, CustomFormHandler handler) {
        super(FormType.CUSTOM);
        this.title = title;
        this.handler = handler;
    }

    /**
     * Set form title
     * @param title Text
     * @return SimpleForm
     */
    public CustomForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Add text element
     * @param text Text
     * @return CustomForm
     */
    public CustomForm addElement(String text) {
        return addElement(new Label(text));
    }

    /**
     * Add form element
     * @param element CustomElement
     * @return CustomForm
     */
    public CustomForm addElement(CustomElement element) {
        elements.add(element);
        return this;
    }

    /**
     * Add form element
     * @param elementId Element identifier
     * @param element   CustomElement
     * @return CustomForm
     */
    public CustomForm addElement(String elementId, CustomElement element) {
        element.setElementId(elementId);
        return addElement(element);
    }

    /**
     * Set form handler
     * @param handler CustomFormHandler
     * @return CustomForm
    */
    public CustomForm setHandler(CustomFormHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void setResponse(String data) {
        if (data.equals("null")) {
            return;
        }

        Object[] result = new Gson().fromJson(data, Object[].class);

        for (int i = 0; i < elements.size(); i++) {
            CustomElement element = elements.get(i);

            if (!element.respond(result[i])) {
                this.response = new CustomFormResponse((player, response) -> send(player), elements, this);
                return;
            }

            if (element instanceof ValidationField && this.validated && !((ValidationField) element).isValidated()) {
                this.validated = false;
            }
        }

        for (int index = 0; index < elements.size(); index++) {
            elements.get(index).setIndex(index);
        }

        this.response = new CustomFormResponse(handler, elements, this);
    }

    public void send(Player player, CustomFormHandler handler) {
        this.setHandler(handler);
        this.send(player);
    }
}
