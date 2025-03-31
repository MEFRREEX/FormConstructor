package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.handler.FormHandler;
import lombok.Getter;

@Getter
public abstract class FormResponse<T extends FormHandler> {

    private final T handler;
    private final String data;

    public FormResponse(T handler, String data) {
        this.handler = handler;
        this.data = data;
    }

    public abstract void handle(Player player);
}
