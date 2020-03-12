package com.haulmont.testtask.view.provider;

import com.haulmont.testtask.view.core.Page;
import com.vaadin.ui.Component;

import java.util.List;

public interface ComponentProvider {
    Component provideMenuBar(Component component, List<Page> items);

    Component provideTitle(Page page);
}
