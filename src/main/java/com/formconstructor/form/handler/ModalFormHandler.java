package com.formconstructor.form.handler;

import cn.nukkit.Player;

public interface ModalFormHandler extends FormHandler {
    void handle(Player player, boolean data);
}