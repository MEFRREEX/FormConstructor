package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.element.simple.Button;

/**
 * Handler for processing simple form submissions.
 */
@FunctionalInterface
public interface SimpleFormHandler extends FormHandler {

    /**
     * Called when a player clicks a button in a simple form.
     *
     * @param player The player who clicked the button
     * @param button The clicked button with its properties and handlers
     */
    void handle(Player player, Button button);
}