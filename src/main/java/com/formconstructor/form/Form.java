package com.formconstructor.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.formconstructor.event.PlayerFormSendEvent;
import com.formconstructor.form.response.FormResponse;
import com.formconstructor.service.FormService;
import com.google.gson.Gson;
import lombok.Getter;

@Getter
public abstract class Form {

    private static final Gson GSON = new Gson();

    private final FormType type;
    private transient boolean async;

    public Form(FormType type) {
        this.type = type;
    }

    public void send(Player player) {
        this.send(player, false);
    }

    public void send(Player player, boolean async) {
        PlayerFormSendEvent event = new PlayerFormSendEvent(player, this, async);
        Server.getInstance().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            this.async = event.isAsync();
            FormService.getInstance().sendForm(player, this);
        }
    }

    public void sendAsync(Player player) {
        this.send(player, true);
    }

    public abstract void setResponse(String data);

    public abstract FormResponse<?> getResponse();

    public String toJson() {
        return GSON.toJson(this);
    }
}