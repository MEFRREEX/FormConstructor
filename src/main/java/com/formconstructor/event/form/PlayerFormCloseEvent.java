package com.formconstructor.event.form;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import com.formconstructor.event.EventCaller;
import com.formconstructor.form.Form;
import com.formconstructor.form.FormCancelReason;
import lombok.Getter;

/**
 * Represents the event of closing the form.
 */
@Getter
public class PlayerFormCloseEvent extends FormEvent implements EventCaller {

    @Getter
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final FormCancelReason cancelReason;

    public PlayerFormCloseEvent(Player player, Form form, FormCancelReason cancelReason) {
        super(form);
        this.player = player;
        this.cancelReason = cancelReason;
    }
}