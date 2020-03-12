package com.haulmont.testtask.view.provider;

import com.haulmont.testtask.view.core.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;

import java.util.List;

public class ComponentProviderImpl implements ComponentProvider {

    @Override
    public Component provideMenuBar(Component component, List<Page> items) {
        MenuBar menu = new MenuBar();
        menu.setWidth(component.getWidth(), component.getWidthUnits());

        items.forEach(item -> {
            MenuBar.Command command = (MenuBar.Command) selectedItem ->
                    component.getUI().getNavigator().navigateTo(item.getUrl());
            menu.addItem(item.getTitle(), null, command);
        });

        return menu;
    }

    @Override
    public Component provideTitle(Page page) {
        HorizontalLayout layout = new HorizontalLayout();
        Label title = new Label(page.getTitle());
        title.addStyleName("h2");
        layout.addComponent(title);
        return layout;
    }
}
