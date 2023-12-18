package ru.contentforge.formconstructor.form;

import ru.contentforge.formconstructor.form.handler.NoneHandler;
import lombok.Getter;

@Getter
public abstract class CloseableForm extends Form {

    public CloseableForm(FormType type) {
        super(type);
    }

    private transient NoneHandler noneHandler;

    public CloseableForm setNoneHandler(NoneHandler noneHandler) {
        this.noneHandler = noneHandler;
        return this;
    }
}