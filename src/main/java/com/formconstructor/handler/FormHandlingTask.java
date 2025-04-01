package com.formconstructor.handler;

import cn.nukkit.Player;
import cn.nukkit.scheduler.AsyncTask;
import com.formconstructor.event.form.PlayerFormCloseEvent;
import com.formconstructor.form.CloseableForm;
import com.formconstructor.form.Form;
import com.formconstructor.form.FormCancelReason;
import com.formconstructor.form.handler.CloseFormHandler;
import com.formconstructor.form.response.CustomFormResponse;
import com.formconstructor.form.response.FormResponse;
import com.formconstructor.form.response.ModalFormResponse;
import com.formconstructor.form.response.SimpleFormResponse;

public class FormHandlingTask extends AsyncTask {

    private final Form form;
    private final FormResponse<?> response;
    private final FormCancelReason cancelReason;
    private final Player player;

    public FormHandlingTask(Form form, FormResponse<?> response, FormCancelReason cancelReason, Player player) {
        this.form = form;
        this.response = response;
        this.cancelReason = cancelReason;
        this.player = player;
    }

    @Override
    public void onRun() {
        if (response == null && form instanceof CloseableForm closeableForm) {
            CloseFormHandler closeHandler = closeableForm.getCloseHandler();

            PlayerFormCloseEvent event = new PlayerFormCloseEvent(player, form, cancelReason);
            event.callEvent();

            if (closeHandler != null) {
                closeHandler.handle(player, cancelReason);
            }
            return;
        }

        if (response instanceof ModalFormResponse modalResponse) {
            modalResponse.handle(player);
            return;
        }

        if (response instanceof SimpleFormResponse simpleResponse) {
            simpleResponse.handle(player);
            return;
        }

        if (response instanceof CustomFormResponse customResponse) {
            customResponse.handle(player);
        }
    }
}