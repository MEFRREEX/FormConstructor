package com.formconstructor.form;

import cn.nukkit.network.protocol.ProtocolInfo;
import com.formconstructor.form.element.ElementSimple;
import com.formconstructor.form.element.general.Divider;
import com.formconstructor.form.element.general.Header;
import com.formconstructor.form.element.general.Label;
import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageType;
import com.formconstructor.form.handler.SimpleFormHandler;
import com.formconstructor.form.response.SimpleFormResponse;
import com.google.gson.*;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Represents a simple form with buttons (and headers, labels,
 * dividers) elements that can be shown to players.
 */
@Getter
public class SimpleForm extends CloseableForm {

    private static final Gson GSON_LEGACY = new GsonBuilder().registerTypeAdapter(SimpleForm.class, new SimpleFormLegacySerializer()).create();

    private String title;
    private String content;

    private final List<ElementSimple> elements = new ArrayList<>();
    
    private transient SimpleFormResponse response;

    /**
     * Creates an empty simple form.
     */
    public SimpleForm() {
        this("");
    }

    /**
     * Creates a simple form with title.
     *
     * @param title The form title
     */
    public SimpleForm(String title) {
        this(title, "");
    }

    /**
     * Creates a simple form with title and content.
     *
     * @param title The form title
     * @param content The form content
     */
    public SimpleForm(String title, String content) {
        super(FormType.SIMPLE);
        this.title = title;
        this.content = content;
    }

    /**
     * Sets the form title.
     *
     * @param title The title text
     * @return This form instance for chaining
     */
    public SimpleForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the form content.
     *
     * @param content The content text
     * @return This form instance for chaining
     */
    public SimpleForm setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Appends text to the form content.
     *
     * @param content The content to append
     * @return This form instance for chaining
     */
    public SimpleForm addContent(String content) {
        this.content += content;
        return this;
    }

    /**
     * Adds a header element to the form.
     *
     * @param header The header text
     * @return This form instance for chaining
     */
    public SimpleForm addHeader(String header) {
        return this.addElement(new Header(header));
    }

    /**
     * Adds a label element to the form.
     *
     * @param label The label text
     * @return This form instance for chaining
     */
    public SimpleForm addLabel(String label) {
        return this.addElement(new Label(label));
    }

    /**
     * Adds a divider element to the form.
     *
     * @return This form instance for chaining
     */
    public SimpleForm addDivider() {
        return this.addElement(new Divider());
    }

    /**
     * Adds a button with text only.
     *
     * @param name The button text
     * @return This form instance for chaining
     */
    public SimpleForm addButton(String name) {
        return this.addButton(name, null);
    }

    /**
     * Adds a button with text and handler.
     *
     * @param name The button text
     * @param handler The click handler
     * @return This form instance for chaining
     */
    public SimpleForm addButton(String name, SimpleFormHandler handler) {
        return this.addButton(name, ImageType.PATH, "", handler);
    }

    /**
     * Adds a button with text and image.
     *
     * @param name The button text
     * @param imageType The type of image
     * @param path The image path
     * @return This form instance for chaining
     */
    public SimpleForm addButton(String name, ImageType imageType, String path) {
        return this.addButton(name, imageType, path, null);
    }

    /**
     * Adds a button with all parameters.
     *
     * @param name The button text
     * @param imageType The type of image
     * @param path The image path
     * @param handler The click handler
     * @return This form instance for chaining
     */
    public SimpleForm addButton(String name, ImageType imageType, String path, SimpleFormHandler handler) {
        return this.addButton(new Button(name, imageType, path, handler));
    }

    /**
     * Adds a pre-configured button.
     *
     * @param button The button to add
     * @return This form instance for chaining
     */
    public SimpleForm addButton(Button button) {
        return this.addElement(button);
    }

    /**
     * Adds multiple buttons.
     *
     * @param buttons The buttons to add
     * @return This form instance for chaining
     */
    public SimpleForm addButtons(Button... buttons) {
        Arrays.asList(buttons).forEach(this::addButton);
        return this;
    }

    /**
     * Adds a collection of buttons.
     *
     * @param buttons The buttons to add
     * @return This form instance for chaining
     */
    public SimpleForm addButtons(Collection<Button> buttons) {
        buttons.forEach(this::addButton);
        return this;
    }

    /**
     * Adds a generic form element.
     *
     * @param element The element to add
     * @return This form instance for chaining
     */
    public SimpleForm addElement(ElementSimple element) {
        this.elements.add(element);
        return this;
    }

    /**
     * Processes the form response.
     *
     * @param data The raw response data
     */
    @Override
    public void setResponse(int protocol, String data) {
        if (data.equals("null")) {
            return;
        }

        List<Button> buttons = elements.stream()
                .filter(element -> element instanceof Button)
                .map(element -> (Button) element)
                .toList();
    
        int buttonId;
        try {
            buttonId = Integer.parseInt(data);
        } catch (NumberFormatException ignored) {
            return;
        }
    
        if (buttonId < 0 || buttonId >= buttons.size()) {
            this.response = new SimpleFormResponse(new Button("Invalid", (p, b) -> send(p)));
            return;
        }

        this.response = new SimpleFormResponse(buttons.get(buttonId));
    }

    /**
     * Serializes the form to JSON based on protocol version.
     *
     * @param protocol The protocol version
     * @return JSON data of the form
     */
    @Override
    public String toJson(int protocol) {
        if (protocol >= ProtocolInfo.v1_21_70_24) {
            return GSON.toJson(this);
        }
        return GSON_LEGACY.toJson(this);
    }

    /**
     * Custom JSON serializer for legacy protocol versions.
     */
    private static class SimpleFormLegacySerializer implements JsonSerializer<SimpleForm> {
        @Override
        public JsonElement serialize(SimpleForm src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = JsonParser.parseString(GSON.toJson(src)).getAsJsonObject();

            List<ElementSimple> filteredButtons = src.elements.stream()
                    .filter(e -> e instanceof Button)
                    .toList();

            jsonObject.remove("elements");
            jsonObject.add("buttons", context.serialize(filteredButtons));
            return jsonObject;
        }
    }
}