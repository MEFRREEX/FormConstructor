package com.formconstructor.form.handler;

import cn.nukkit.Player;

/**
 * Handler for processing when a form is closed without submission.
 */
@FunctionalInterface
public interface CloseFormHandler extends FormHandler {

    /**
     * Called when a player closes a form without submitting it.
     *
     * @param player The player who closed the form
     */
    void handle(Player player);
}