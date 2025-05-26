package com.formconstructor.event.form;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import com.formconstructor.event.EventCaller;
import com.formconstructor.form.Form;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the event of sending in a player's form.
 */
@Getter
@Setter
public class PlayerFormSendEvent extends FormEvent implements Cancellable, EventCaller {

    @Getter
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private boolean async;

    public PlayerFormSendEvent(Player player, Form form, boolean async) {
        super(form);
        this.player = player;
        this.async = async;
    }
}
