package com.formconstructor.form;

import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageType;
import com.formconstructor.form.handler.CloseFormHandler;
import com.formconstructor.form.handler.SimpleFormHandler;
import com.formconstructor.form.response.SimpleFormResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
public class SimpleForm extends CloseableForm {

    private String title;
    private String content;
    private final List<Button> buttons = new ArrayList<>();
    
    private transient SimpleFormResponse response;

    public SimpleForm() {
        this("");
    }

    public SimpleForm(String title) {
        this(title, "");
    }

    public SimpleForm(String title, String content) {
        this(title, content, null);
    }

    public SimpleForm(String title, String content, CloseFormHandler closeHandler) {
        this(title, content, closeHandler, null);
    }

    public SimpleForm(String title, String content, CloseFormHandler closeHandler, Collection<Button> buttons) {
        super(FormType.SIMPLE);
        this.title = title;
        this.content = content;
        this.setCloseHandler(closeHandler);

        if (buttons != null) {
            this.buttons.addAll(buttons);
        }
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
     * Add a button to the form
     * @param name Button name
     * @return SimpleForm
     */
    public SimpleForm addButton(String name) {
        return addButton(name, null);
    }

    /**
     * Add a button to the form
     * @param name    Button name
     * @param handler Button handler
     * @return SimpleForm
     */
    public SimpleForm addButton(String name, SimpleFormHandler handler) {
        return addButton(name, ImageType.PATH, "", handler);
    }

    /**
     * Add a button to the form
     * @param name      Button name
     * @param imageType Type of image on button
     * @param path      Path to image on button
     * @return SimpleForm
     */
    public SimpleForm addButton(String name, ImageType imageType, String path) {
        return addButton(name, imageType, path, null);
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
        return addButton(new Button(name, imageType, path, handler));
    }

    /**
     * Add a button to the form
     * @param button Button
     * @return SimpleForm
     */
    public SimpleForm addButton(Button button) {
        this.buttons.add(button);
        return this;
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

    @Override
    public void setResponse(String data) {
        if (data.equals("null")) {
            return;
        }
    
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
    
        for (int index = 0; index < buttons.size(); index++) {
            buttons.get(index).setIndex(index);
        }

        this.response = new SimpleFormResponse(buttons.get(buttonId));
    }
}