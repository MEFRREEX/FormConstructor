package com.formconstructor.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.scheduler.AsyncTask;
import com.formconstructor.form.CloseableForm;
import com.formconstructor.form.Form;
import com.formconstructor.form.handler.CloseFormHandler;
import com.formconstructor.form.response.CustomFormResponse;
import com.formconstructor.form.response.ModalFormResponse;
import com.formconstructor.form.response.SimpleFormResponse;
import com.formconstructor.event.PlayerFormCloseEvent;

public class FormHandlingTask extends AsyncTask {

    private final FormResponse response;
    private final Form form;
    private final Player player;

    public FormHandlingTask(FormResponse response, Form form, Player player) {
        this.response = response;
        this.form = form;
        this.player = player;
    }

    @Override
    public void onRun() {
        if (response == null && form instanceof CloseableForm closeableForm) {
            CloseFormHandler closeHandler = closeableForm.getCloseHandler();
            
            PlayerFormCloseEvent event = new PlayerFormCloseEvent(player, form);
            Server.getInstance().getPluginManager().callEvent(event);
            
            if (closeHandler != null) closeHandler.handle(player);
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