package com.formconstructor.service;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.ClientboundCloseFormPacket;
import cn.nukkit.network.protocol.ModalFormRequestPacket;
import cn.nukkit.network.protocol.ServerSettingsResponsePacket;
import com.formconstructor.form.Form;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class FormServiceImpl implements FormService {

    private final AtomicInteger nextFormId = new AtomicInteger();
    private final Map<Integer, Form> storedForms = new HashMap<>();
    private final Map<Player, Set<Integer>> playerForms = new HashMap<>();

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
        playerForms.forEach((player, formIds) -> formIds.remove(formId));
        playerForms.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        return storedForms.remove(formId);
    }

    @Override
    public void sendForm(Player player, Form form) {
        this.sendForm(player, form, this.getNextFormId());
    }

    @Override
    public void sendForm(Player player, Form form, int formId) {
        this.storedForms.put(formId, form);
        this.playerForms.computeIfAbsent(player, p -> new HashSet<>()).add(formId);

        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.formId = formId;
        packet.data = form.toJson(player.protocol);
        player.dataPacket(packet);
    }

    @Override
    public void sendUpdate(Player player, Form form) {
        Optional<Integer> formId = storedForms.entrySet().stream()
                .filter(entry -> entry.getValue().equals(form))
                .map(Map.Entry::getKey)
                .findFirst();

        if (formId.isEmpty()) {
            log.warn("Attempting to update a non-existing form for a player {}", player.getName());
            return;
        }

        // Exploiting some (probably unintended) protocol features here
        // Thanks to https://github.com/PowerNukkitX/PowerNukkitX for this code!
        ServerSettingsResponsePacket packet = new ServerSettingsResponsePacket();
        packet.formId = formId.get();
        packet.data = form.toJson();
        player.dataPacket(packet);
    }

    @Override
    public void closeForms(Player player) {
        Set<Integer> formIds = playerForms.remove(player);
        if (formIds != null) {
            for (int formId : formIds) {
                storedForms.remove(formId);
            }
        }
        player.dataPacket(new ClientboundCloseFormPacket());
    }

    @Override
    public int getNextFormId() {
        return nextFormId.incrementAndGet();
    }
}
