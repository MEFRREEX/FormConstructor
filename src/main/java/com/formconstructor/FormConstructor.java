package com.formconstructor;

import cn.nukkit.plugin.PluginBase;
import com.formconstructor.form.SimpleForm;
import com.formconstructor.handler.FormResponseHandler;
import com.formconstructor.service.FormService;
import com.formconstructor.service.FormServiceImpl;
import lombok.Getter;

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

        SimpleForm form = new SimpleForm();

        form.setCloseHandler((pl, r) -> {

        });
    }
}