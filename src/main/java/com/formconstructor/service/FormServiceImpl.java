package com.formconstructor.service;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.ModalFormRequestPacket;
import com.formconstructor.form.Form;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FormServiceImpl implements FormService {

    private final AtomicInteger nextFormId = new AtomicInteger();
    private final Map<Integer, Form> storedForms = new HashMap<>();

    @Override
    public void addStoredForm(int formId, Form form) {
        storedForms.put(formId, form);
    }

    @Override
    public Form getStoredForm(int formId) {
        return storedForms.get(formId);
    }

    @Override
    public Form getAndRemoveStoredForm(int formId) {
        return storedForms.remove(formId);
    }

    @Override
    public void sendForm(Player player, Form form) {
        this.sendForm(player, form, this.getNextFormId());
    }

    @Override
    public void sendForm(Player player, Form form, int formId) {
        this.storedForms.put(formId, form);

        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.formId = formId;
        packet.data = form.toJson(player.protocol);
        player.dataPacket(packet);
    }

    @Override
    public int getNextFormId() {
        return nextFormId.incrementAndGet();
    }
}
