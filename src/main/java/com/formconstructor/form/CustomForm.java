package com.formconstructor.form;

import cn.nukkit.Player;
import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.general.Label;
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
    private final List<ElementCustom> elements = new ArrayList<>();

    @Getter
    private transient boolean validated = true;

    private transient CustomFormHandler handler;
    private transient CustomFormResponse response;

    public CustomForm() {
        this("");
    }

    public CustomForm(String title) {
        super(FormType.CUSTOM);
        this.title = title;
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
        return this.addElement(new Label(text));
    }

    /**
     * Add form element
     * @param element CustomElement
     * @return CustomForm
     */
    public CustomForm addElement(ElementCustom element) {
        return this.addElement(element.getName(), element);
    }

    /**
     * Add form element
     * @param elementId Element identifier
     * @param element   CustomElement
     * @return CustomForm
     */
    public CustomForm addElement(String elementId, ElementCustom element) {
        elements.add(element);
        if (element instanceof ElementIdentifiable elementIdentifiable) {
            elementIdentifiable.setElementId(elementId);
        }
        return this;
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
            ElementCustom element = elements.get(i);

            if (!element.respond(result[i])) {
                this.response = new CustomFormResponse((player, response) -> send(player), elements, this);
                return;
            }

            if (element instanceof ValidationField && this.validated && !((ValidationField) element).isValidated()) {
                this.validated = false;
            }
        }

        this.response = new CustomFormResponse(handler, elements, this);
    }

    public void send(Player player, CustomFormHandler handler) {
        this.setHandler(handler);
        this.send(player);
    }
}
