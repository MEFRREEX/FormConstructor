package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.handler.ModalFormHandler;

/**
 * Represents a response from a modal form submission.
 */
public class ModalFormResponse extends FormResponse<ModalFormHandler> {

    /**
     * Creates a new modal form response.
     *
     * @param handler The handler to process this response
     * @param data The raw response data ("true" or "false")
     */
    public ModalFormResponse(ModalFormHandler handler, String data) {
        super(handler, data);
    }

    /**
     * Processes the modal form response for the player.
     *
     * @param player The player who submitted the form
     */
    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, this.getData().equals("true"));
        }
    }
}
