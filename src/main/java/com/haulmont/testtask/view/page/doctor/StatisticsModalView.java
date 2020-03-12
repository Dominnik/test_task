package com.haulmont.testtask.view.page.doctor;

import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.view.core.Column;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.Arrays;
import java.util.List;

public class StatisticsModalView extends Window {

    private Grid grid;

    public StatisticsModalView() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        setClosable(true);
        setResizable(false);
        setHeight("800px");
        setWidth("800px");
        center();
        setCaption("Статистика");
        initTable(layout);
    }

    private void initTable(VerticalLayout layout) {
        grid = new Grid();
        List<Column> columns = getColumns();
        columns.forEach(column -> grid.addColumn(column.getId(), column.getType())
                .setHeaderCaption(column.getCaption())
                .setHidden(column.isHidden()));
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setSizeFull();
        layout.addComponent(grid);
        setContent(layout);
    }

    private List<Column> getColumns() {
        return Arrays.asList(
                new Column("id", "Идентификатор", true, Long.class),
                new Column("lastName", "Фамилия", false, String.class),
                new Column("firstName", "Имя", false, String.class),
                new Column("middleName", "Отчество", false, String.class),
                new Column("prescriptionCount", "Количество рецептов", false, Integer.class)
        );
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        grid.getContainerDataSource().removeAllItems();
        doctors.forEach(doctor -> grid.addRow(
                doctor.getId(),
                doctor.getLastName(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getPrescriptions().size())
        );
    }
}
