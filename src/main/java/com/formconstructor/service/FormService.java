package com.formconstructor.service;

import cn.nukkit.Player;
import com.formconstructor.FormConstructor;
import com.formconstructor.form.Form;

public interface FormService {

    void addStoredForm(int formId, Form form);

    Form getStoredForm(int formId);

    Form getAndRemoveStoredForm(int formId);

    void sendForm(Player player, Form form);

    void sendForm(Player player, Form form, int formId);

    int getNextFormId();

    static FormService getInstance() {
        return FormConstructor.getInstance().getFormService();
    }
}
