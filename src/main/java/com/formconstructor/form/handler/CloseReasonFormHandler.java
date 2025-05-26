package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.FormCancelReason;

/**
 * Handler for processing when a form is closed without submission.
 */
@FunctionalInterface
public interface CloseReasonFormHandler extends FormHandler {

    /**
     * Called when a player closes a form without submitting it.
     *
     * @param player The player who closed the form
     * @param reason The reason for closing the form
     */
    void handle(Player player, FormCancelReason reason);

    /**
     * Creates a form close handler with a reason without needing to handle it.
     *
     * @param closeHandler The CloseFormHandler handler
     * @return The CloseReasonFormHandler handler with reason
     */
    static CloseReasonFormHandler withoutReason(CloseFormHandler closeHandler) {
        return (player, reason) -> closeHandler.handle(player);
    }
}