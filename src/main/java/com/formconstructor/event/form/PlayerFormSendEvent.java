package com.formconstructor.event.form;

import cn.nukkit.Player;
import com.formconstructor.event.EventCaller;
import com.formconstructor.form.Form;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import lombok.Setter;
import lombok.Getter;

/**
 * Represents the event of sending in a player's form.
 */
@Getter
@Setter
public class PlayerFormSendEvent extends FormEvent implements Cancellable, EventCaller {
 
    private final Player player;
    private boolean async;

    @Getter
    private static final HandlerList handlers = new HandlerList();

    public PlayerFormSendEvent(Player player, Form form, boolean async) {
        super(form);
        this.player = player;
        this.async = async;
    }
}
