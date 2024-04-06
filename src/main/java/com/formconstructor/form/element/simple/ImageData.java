package com.formconstructor.form.element.simple;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ImageData {

    @SerializedName("type") private final ImageType type;
    @SerializedName("data") private final String path;

    public ImageData() {
        this(ImageType.PATH, "");
    }

    public ImageData(ImageType type, String path) {
        this.path = path;
        this.type = type;
    }
}