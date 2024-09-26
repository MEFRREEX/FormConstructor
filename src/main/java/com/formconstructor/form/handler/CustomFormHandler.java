package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.response.CustomFormResponse;

public interface CustomFormHandler extends FormHandler {
    void handle(Player player, CustomFormResponse response);
}