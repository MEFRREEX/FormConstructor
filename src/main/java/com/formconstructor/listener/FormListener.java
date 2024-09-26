package com.formconstructor.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import com.formconstructor.FormConstructor;
import com.formconstructor.form.Form;
import com.formconstructor.task.FormHandlingTask;

public class FormListener implements Listener {
 
    private final FormConstructor main;

    public FormListener(FormConstructor main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFormResponded(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof Form form) {
            FormHandlingTask handler = new FormHandlingTask(event.getResponse(), form, event.getPlayer());

            if (form.isAsync()) {
                main.getServer().getScheduler().scheduleAsyncTask(main, handler);
            } else {
                handler.onRun();
            }
        }
    }
}
