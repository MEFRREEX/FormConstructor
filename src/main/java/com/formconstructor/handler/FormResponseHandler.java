package com.formconstructor.handler;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.ModalFormResponsePacket;
import com.formconstructor.FormConstructor;
import com.formconstructor.form.Form;
import com.formconstructor.service.FormService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormResponseHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPacket(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();

        if (event.getPacket() instanceof ModalFormResponsePacket packet) {
            Form storedForm = FormService.getInstance().getAndRemoveStoredForm(packet.formId);
            if (storedForm != null) {
                try {
                    String formData = packet.data;
                    storedForm.setResponse(formData != null ? formData.trim() : null);

                    FormHandlingTask formHandlingTask = new FormHandlingTask(storedForm.getResponse(), storedForm, player);
                    if (storedForm.isAsync()) {
                        Server.getInstance().getScheduler().scheduleAsyncTask(FormConstructor.getInstance(), formHandlingTask);
                    } else {
                        formHandlingTask.onRun();
                    }

                } catch (Exception e) {
                    log.error("Error while processing form response with id {} for player {}", packet.formId, player.getName(), e);
                }
            } else {
                log.warn("Couldn't find stored form with id {} for player {}", packet.formId, player.getName());
            }
        }
    }
}
