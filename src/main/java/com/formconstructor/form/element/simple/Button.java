package com.formconstructor.form.element.simple;

import com.formconstructor.form.element.FormElement;
import com.formconstructor.form.handler.SimpleFormHandler;
import lombok.Getter;

@Getter 
public class Button extends FormElement {

    private ImageData image;
    
    private transient SimpleFormHandler handler;

    public Button() {
        this("");
    }

    public Button(String name) {
        this(name, null);
    }

    public Button(String name, SimpleFormHandler handler) {
        this(name, ImageType.PATH, "", handler);
    }

    public Button(String name, ImageType imageType, String image) {
        this(name, imageType, image, null);
    }

    public Button(String name, ImageType imageType, String image, SimpleFormHandler handler) {
        super(name);
        this.image = new ImageData(imageType, image);
        this.handler = handler;
    }

    @Override
    public Button setName(String name) {
        return (Button) super.setName(name);
    }

    /**
     * Set button image
     * @param image ImageData
     * @return Button
     */
    public Button setImage(ImageData image) {
        this.image = image;
        return this;
    }

    /**
     * Set button image
     * @param type Type of image on button
     * @param path Path to image on button
     * @return Button
     */
    public Button setImage(ImageType type, String path) {
        return setImage(new ImageData(type, path));
    }

    /**
     * Set the button click handler
     * @param handler SimpleFormHandler
     * @return Button
     */
    public Button onClick(SimpleFormHandler handler) {
        this.handler = handler;
        return this;
    }
}