package com.formconstructor.event.form;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import com.formconstructor.event.EventCaller;
import com.formconstructor.form.Form;
import lombok.Getter;

/**
 * Represents the event of closing the form by the player or forcibly.
 */
@Getter
public class PlayerFormCloseEvent extends FormEvent implements EventCaller {
 
    private final Player player;

    @Getter
    private static final HandlerList handlers = new HandlerList();

    public PlayerFormCloseEvent(Player player, Form form) {
        super(form);
        this.player = player;
    }
}