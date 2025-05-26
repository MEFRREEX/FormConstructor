package com.formconstructor.form.element.simple;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ImageData {

    private static final ImageData EMPTY = new ImageData();

    @SerializedName("type")
    private final ImageType type;

    @SerializedName("data")
    private final String path;

    public ImageData() {
        this(ImageType.PATH, "");
    }

    public ImageData(ImageType type, String path) {
        this.path = path;
        this.type = type;
    }

    public static ImageData texture(String path) {
        return new ImageData(ImageType.PATH, path);
    }

    public static ImageData url(String url) {
        return new ImageData(ImageType.URL, url);
    }

    public static ImageData empty() {
        return EMPTY;
    }
}