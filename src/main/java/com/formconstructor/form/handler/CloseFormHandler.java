package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.FormCancelReason;

/**
 * Handler for processing when a form is closed without submission.
 */
public interface CloseFormHandler extends FormHandler {

    /**
     * Called when a player closes a form without submitting it.
     *
     * @param player The player who closed the form
     */
    void handle(Player player, FormCancelReason reason);
}