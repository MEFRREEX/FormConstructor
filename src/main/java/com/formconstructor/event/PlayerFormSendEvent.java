package com.formconstructor.event;

import cn.nukkit.Player;
import com.formconstructor.form.Form;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class PlayerFormSendEvent extends FormEvent implements Cancellable {
 
    private final Player player;
    private boolean async;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerFormSendEvent(Player player, Form form, boolean async) {
        super(form);
        this.player = player;
        this.async = async;
    }
}
