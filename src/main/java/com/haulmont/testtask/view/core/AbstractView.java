package com.haulmont.testtask.view.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.entity.Dto;
import com.haulmont.testtask.model.repository.Repository;
import com.haulmont.testtask.view.module.ViewModule;
import com.haulmont.testtask.view.provider.ComponentProvider;
import com.sun.istack.NotNull;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractView<T extends Dto> extends VerticalLayout implements View, Repository.UpdateEntityListener {

    private Grid grid;
    private Button removeButton;
    private Button editButton;

    protected Repository<T> repository;
    protected Controller<T> controller;
    private ComponentProvider componentProvider;
    private BeanItemContainer<T> dataContainer;

    public AbstractView(Class<T> genericClass) {
        Injector injector = Guice.createInjector(new ViewModule());
        inject(injector);
        repository.addUpdateListener(this);
        dataContainer = new BeanItemContainer<>(genericClass, repository.getAll());

        setSpacing(true);
        addComponent(componentProvider.provideMenuBar(this, Arrays.asList(Page.values())));
        Component title = componentProvider.provideTitle(getPage());
        addComponent(title);
        setComponentAlignment(title, Alignment.TOP_CENTER);

        initTopComponents();
        initTable();
        initButtons();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }

    protected void initTopComponents() {
    }

    protected void inject(Injector injector) {
        componentProvider = injector.getInstance(ComponentProvider.class);
    }

    protected abstract Page getPage();

    protected abstract List<Column> getColumns();

    private void initButtons() {
        Button addButton = new Button("Добавить", FontAwesome.PLUS);
        addButton.addClickListener(e -> onAdd());

        editButton = new Button("Редактировать", FontAwesome.PENCIL);
        editButton.setVisible(false);
        editButton.addClickListener(e -> {
            Object selected = grid.getSelectedRow();
            T bean = dataContainer.getItem(selected).getBean();
            onEdit(bean);
        });

        removeButton = new Button("Удалить", FontAwesome.TRASH);
        removeButton.setVisible(false);
        removeButton.addClickListener(e -> {
            Object selected = grid.getSelectedRow();
            T bean = dataContainer.getItem(selected).getBean();
            onRemove(bean);
        });

        HorizontalLayout operationButtons = new HorizontalLayout();
        operationButtons.setSpacing(true);
        operationButtons.setMargin(true);

        List<Button> buttons = new ArrayList<>(Arrays.asList(addButton, editButton, removeButton));
        buttons.addAll(getAdvancedButtons());
        operationButtons.addComponents(buttons.toArray(new Button[0]));
        addComponent(operationButtons);
        setComponentAlignment(operationButtons, Alignment.TOP_RIGHT);
    }

    @NotNull
    protected List<Button> getAdvancedButtons() {
        return new ArrayList<>();
    }

    private void initTable() {
        grid = new Grid(dataContainer);
        grid.setWidth(getWidth(), getWidthUnits());

        List<Column> columns = getColumns();
        grid.setColumnOrder(columns.stream().map(Column::getId).toArray());
        columns.forEach(column ->
                grid.getColumn(column.getId())
                        .setHeaderCaption(column.getCaption())
                        .setHidden(column.isHidden()));


        grid.addSelectionListener(e -> {
            boolean hasSelected = !e.getSelected().isEmpty();
            editButton.setVisible(hasSelected);
            removeButton.setVisible(hasSelected);
        });
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        addComponents(grid);
        setComponentAlignment(grid, Alignment.TOP_CENTER);
    }

    @Override
    public void onUpdated() {
        dataContainer.removeAllItems();
        dataContainer.addAll(repository.getAll());
    }

    protected abstract void onEdit(T entity);

    protected abstract void onAdd();

    private void onRemove(T entity) {
        controller.delete(entity.getId());
    }

    protected void applyFilter(String propertyId, String searchString) {
        dataContainer.removeContainerFilters(propertyId);
        SimpleStringFilter filter = new SimpleStringFilter(
                propertyId,
                searchString,
                true,
                false
        );
        dataContainer.addContainerFilter(filter);
    }

    protected List<T> getTableItems() {
        return dataContainer.getItemIds();
    }

}
