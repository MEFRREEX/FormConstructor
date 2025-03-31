package com.formconstructor;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJumpEvent;
import cn.nukkit.plugin.PluginBase;
import com.formconstructor.form.SimpleForm;
import com.formconstructor.form.element.general.Divider;
import com.formconstructor.form.element.general.Header;
import com.formconstructor.form.element.general.Label;
import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageData;
import com.formconstructor.handler.FormResponseHandler;
import com.formconstructor.service.FormService;
import com.formconstructor.service.FormServiceImpl;
import lombok.Getter;

@Getter
public class FormConstructor extends PluginBase implements Listener {

    @Getter
    private static FormConstructor instance;

    private FormService formService;

    @Override
    public void onLoad() {
        FormConstructor.instance = this;
    }

    @Override
    public void onEnable() {
        this.formService = new FormServiceImpl();
        this.getServer().getPluginManager().registerEvents(new FormResponseHandler(), this);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJumpEvent event) {
        Player player = event.getPlayer();

        SimpleForm form = new SimpleForm("Test")
                .addContent("Form content")
                .addHeader("Header")
                .addLabel("Sample label")
                .addDivider()
                .addButton(new Button()
                        .setName("Example")
                        .setImage(ImageData.texture("textures/items/apple"))
                        .onClick((pl, b) -> pl.sendMessage("You clicked example button")))
                .addElement(new Label("Label after the button"));
        form.send(player);
    }
}