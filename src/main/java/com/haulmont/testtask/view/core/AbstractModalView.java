package com.haulmont.testtask.view.core;

import com.haulmont.testtask.model.entity.Dto;
import com.vaadin.ui.*;

public abstract class AbstractModalView<T extends Dto> extends Window {

    protected OnClickListener<T> listener;

    public AbstractModalView(T entity) {
        setClosable(false);
        setResizable(false);
        setWidth("400");
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        initFields(layout);
        initButtons(layout);
        center();

        setValue(entity);
    }

    protected abstract void initFields(VerticalLayout layout);

    protected abstract void initButtons(VerticalLayout layout);

    protected abstract void setValue(T entity);

    protected abstract boolean isValid();

    public void setOnOkListener(OnClickListener<T> listener) {
        this.listener = listener;
    }

}
