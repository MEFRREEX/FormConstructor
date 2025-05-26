package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.handler.FormHandler;
import lombok.Getter;

/**
 * Abstract base class for form responses.
 *
 * @param <T> Type of form handler that processes this response
 */
@Getter
public abstract class FormResponse<T extends FormHandler> {

    private final T handler;
    private final String data;

    /**
     * Creates a new form response instance.
     *
     * @param handler Handler for processing the response
     * @param data Raw response data from the form
     */
    public FormResponse(T handler, String data) {
        this.handler = handler;
        this.data = data;
    }

    /**
     * Processes the form response for a specific player.
     *
     * @param player The player who submitted the form
     */
    public abstract void handle(Player player);
}
