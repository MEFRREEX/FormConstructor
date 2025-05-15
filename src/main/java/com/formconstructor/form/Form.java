package com.formconstructor.form;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.ProtocolInfo;
import com.formconstructor.event.form.PlayerFormSendEvent;
import com.formconstructor.form.response.FormResponse;
import com.formconstructor.service.FormService;
import com.google.gson.Gson;
import lombok.Getter;

/**
 * Abstract base class for creating and managing forms.
 */
@Getter
public abstract class Form {

    protected static final Gson GSON = new Gson();

    private final FormType type;
    private transient boolean async;

    /**
     * Creates a form with type.
     */
    public Form(FormType type) {
        this.type = type;
    }

    /**
     * Sends the form to a player synchronously.
     *
     * @param player The player to send the form to
     */
    public void send(Player player) {
        this.send(player, false);
    }

    /**
     * Sends the form to a player with async option.
     *
     * @param player The player to send the form to
     * @param async Whether to send the form asynchronously
     */
    public void send(Player player, boolean async) {
        PlayerFormSendEvent event = new PlayerFormSendEvent(player, this, async);

        if (event.callEvent()) {
            this.async = event.isAsync();
            FormService.getInstance().sendForm(player, this);
        }
    }

    /**
     * Sends the form to a player asynchronously.
     *
     * @param player The player to send the form to
     */
    public void sendAsync(Player player) {
        this.send(player, true);
    }

    /**
     * Sends the form update to a player.
     *
     * @param player The player to send the form update to
     */
    public void sendUpdate(Player player) {
        FormService.getInstance().sendUpdate(player, this);
    }

    /**
     * Sets the form response from raw data.
     *
     * @param protocol The response protocol version
     * @param data The raw response data
     */
    public abstract void setResponse(int protocol, String data);

    /**
     * Gets the parsed form response.
     *
     * @return The form response object
     */
    public abstract FormResponse<?> getResponse();

    /**
     * Converts the form to JSON using current server protocol.
     *
     * @return JSON data of the form
     */
    public String toJson() {
        return this.toJson(ProtocolInfo.CURRENT_PROTOCOL);
    }

    /**
     * Converts the form to JSON for specific protocol version.
     *
     * @param protocol The protocol version to use
     * @return JSON data of the form
     */
    public String toJson(int protocol) {
        return GSON.toJson(this);
    }
}