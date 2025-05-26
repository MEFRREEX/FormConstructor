package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.handler.SimpleFormHandler;

/**
 * Represents a response from a simple form submission.
 */
public class SimpleFormResponse extends FormResponse<SimpleFormHandler> {

    private final Button button;

    /**
     * Creates a new simple form response.
     *
     * @param button The button that was clicked by the player
     */
    public SimpleFormResponse(Button button) {
        super(button.getHandler(), "");
        this.button = button;
    }

    /**
     * Processes the simple form response for the player.
     *
     * @param player The player who submitted the form
     */
    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, button);
        }
    }
}