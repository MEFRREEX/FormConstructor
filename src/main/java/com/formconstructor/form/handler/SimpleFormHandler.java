package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.element.simple.Button;

public interface SimpleFormHandler extends FormHandler {
    void handle(Player player, Button button);
}