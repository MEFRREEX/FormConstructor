package com.formconstructor;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import com.formconstructor.form.CustomForm;
import com.formconstructor.form.ModalForm;
import com.formconstructor.form.SimpleForm;
import com.formconstructor.form.element.SelectableElement;
import com.formconstructor.form.element.custom.*;
import com.formconstructor.form.element.general.Divider;
import com.formconstructor.form.element.general.Header;
import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageType;
import com.formconstructor.handler.FormResponseHandler;
import com.formconstructor.service.FormService;
import com.formconstructor.service.FormServiceImpl;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class FormConstructor extends PluginBase {

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
        this.getServer().getCommandMap().register("", new Command("test") {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                SimpleForm form = new SimpleForm("Sample title");

                AtomicInteger counter = new AtomicInteger();

// For example, let's create a task that will increment the counter by 1 every second
                Server.getInstance().getScheduler().scheduleRepeatingTask(() -> {
                    form.setContent("Count is " + counter.get()); // Set form content
                    form.sendUpdate((Player) sender); // Send a form update
                    counter.getAndIncrement();
                }, 20);

                form.send((Player) sender);
                return false;
            }
        });
    }
}