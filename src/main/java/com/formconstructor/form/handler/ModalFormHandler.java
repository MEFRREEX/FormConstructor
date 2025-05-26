package com.formconstructor.form.handler;

import cn.nukkit.Player;

/**
 * Handler for processing modal form submissions.
 */
@FunctionalInterface
public interface ModalFormHandler extends FormHandler {

    /**
     * Called when a player submits a modal form.
     *
     * @param player The player who submitted the form
     * @param result True if positive button was clicked, false otherwise
     */
    void handle(Player player, boolean result);
}