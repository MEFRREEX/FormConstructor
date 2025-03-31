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
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Getter
public class SimpleForm extends CloseableForm {

    private static final Gson GSON_LEGACY = new GsonBuilder().registerTypeAdapter(SimpleForm.class, new SimpleFormLegacySerializer()).create();

    private String title;
    private String content;

    private final List<ElementSimple> elements = new ArrayList<>();
    
    private transient SimpleFormResponse response;

    public SimpleForm() {
        this("");
    }

    public SimpleForm(String title) {
        this(title, "");
    }

    public SimpleForm(String title, String content) {
        super(FormType.SIMPLE);
        this.title = title;
        this.content = content;
    }

    /**
     * Set form title
     * @param title Text
     * @return SimpleForm
     */
    public SimpleForm setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set form content
     * @param content Text
     * @return SimpleForm
     */
    public SimpleForm setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Add form content
     * @param content Text
     * @return SimpleForm
     */
    public SimpleForm addContent(String content) {
        this.content += content;
        return this;
    }

    /**
     * Add form header element
     * @param header Header text
     * @return SimpleForm
     */
    public SimpleForm addHeader(String header) {
        return this.addElement(new Header(header));
    }

    /**
     * Add form label element
     * @param label Label text
     * @return SimpleForm
     */
    public SimpleForm addLabel(String label) {
        return this.addElement(new Label(label));
    }

    /**
     * Add form divider element
     * @return SimpleForm
     */
    public SimpleForm addDivider() {
        return this.addElement(new Divider());
    }

    /**
     * Add a button to the form
     * @param name Button name
     * @return SimpleForm
     */
    public SimpleForm addButton(String name) {
        return this.addButton(name, null);
    }

    /**
     * Add a button to the form
     * @param name    Button name
     * @param handler Button handler
     * @return SimpleForm
     */
    public SimpleForm addButton(String name, SimpleFormHandler handler) {
        return this.addButton(name, ImageType.PATH, "", handler);
    }

    /**
     * Add a button to the form
     * @param name      Button name
     * @param imageType Type of image on button
     * @param path      Path to image on button
     * @return SimpleForm
     */
    public SimpleForm addButton(String name, ImageType imageType, String path) {
        return this.addButton(name, imageType, path, null);
    }

    /**
     * Add a button to the form
     * @param name      Button name
     * @param imageType Type of image on button
     * @param path      Path to image on button
     * @param handler   Button handler
     * @return SimpleForm
     */
    public SimpleForm addButton(String name, ImageType imageType, String path, SimpleFormHandler handler) {
        return this.addButton(new Button(name, imageType, path, handler));
    }

    /**
     * Add a button to the form
     * @param button Button
     * @return SimpleForm
     */
    public SimpleForm addButton(Button button) {
        return this.addElement(button);
    }

    /**
     * Add a buttons to the form
     * @param buttons Button array
     * @return SimpleForm
     */
    public SimpleForm addButtons(Button... buttons) {
        Arrays.asList(buttons).forEach(this::addButton);
        return this;
    }

    /**
     * Add a buttons to the form
     * @param buttons Collection of button
     * @return SimpleForm
     */
    public SimpleForm addButtons(Collection<Button> buttons) {
        buttons.forEach(this::addButton);
        return this;
    }

    /**
     * Add an element to the form
     * @param element ElementSimple
     * @return SimpleForm
     */
    public SimpleForm addElement(ElementSimple element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public void setResponse(String data) {
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

    @Override
    public String toJson(int protocol) {
        if (protocol >= ProtocolInfo.v1_21_70_24) {
            return GSON.toJson(this);
        }
        return GSON_LEGACY.toJson(this);
    }

    public static class SimpleFormLegacySerializer implements JsonSerializer<SimpleForm> {
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