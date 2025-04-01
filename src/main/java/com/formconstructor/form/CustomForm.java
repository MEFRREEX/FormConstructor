package com.formconstructor.form;

import cn.nukkit.network.protocol.ProtocolInfo;
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

/**
 * Represents a custom form with inputs, toggles, dropdowns etc.
 */
@Getter
public class CustomForm extends CloseableForm {

    private String title;

    @SerializedName("content")
    private final List<ElementCustom> elements = new ArrayList<>();

    @Getter
    private transient boolean validated = true;
    private transient CustomFormHandler handler;
    private transient CustomFormResponse response;

    /**
     * Creates an empty custom form.
     */
    public CustomForm() {
        this("");
    }

    /**
     * Creates a custom form with title.
     *
     * @param title The form title
     */
    public CustomForm(String title) {
        super(FormType.CUSTOM);
        this.title = title;
    }

    /**
     * Sets the form title.
     *
     * @param title The title text
     * @return This form instance for chaining
     */
    public CustomForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Adds a text label element to the form.
     *
     * @param text The label text
     * @return This form instance for chaining
     */
    public CustomForm addElement(String text) {
        return this.addElement(new Label(text));
    }

    /**
     * Adds a custom form element.
     *
     * @param element The element to add
     * @return This form instance for chaining
     */
    public CustomForm addElement(ElementCustom element) {
        return this.addElement(element.getName(), element);
    }

    /**
     * Adds a custom form element with specified ID.
     *
     * @param elementId The unique identifier for the element
     * @param element The element to add
     * @return This form instance for chaining
     */
    public CustomForm addElement(String elementId, ElementCustom element) {
        elements.add(element);
        if (element instanceof ElementIdentifiable elementIdentifiable) {
            elementIdentifiable.setElementId(elementId);
        }
        return this;
    }

    /**
     * Sets the form submission handler.
     *
     * @param handler The handler to process form responses
     * @return This form instance for chaining
     */
    public CustomForm setHandler(CustomFormHandler handler) {
        this.handler = handler;
        return this;
    }

    /**
     * Processes the form response data.
     *
     * @param data The raw response data from client
     */
    @Override
    public void setResponse(int protocol, String data) {
        if (data.equals("null")) {
            return;
        }

        Object[] result = new Gson().fromJson(data, Object[].class);

        int index = 0;
        for (ElementCustom element : elements) {
            if (!element.respond(result[index])) {
                this.response = new CustomFormResponse((player, response) -> send(player), elements, this);
                return;
            }

            if (element instanceof ValidationField && this.validated && !((ValidationField) element).isValidated()) {
                this.validated = false;
            }

            if (!(element instanceof Label) || protocol < ProtocolInfo.v1_21_70_24) {
                index++;
            }
        }

        this.response = new CustomFormResponse(handler, elements, this);
    }
}
