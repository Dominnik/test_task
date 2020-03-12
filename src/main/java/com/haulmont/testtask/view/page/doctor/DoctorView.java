package com.haulmont.testtask.view.page.doctor;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.model.repository.Repository;
import com.haulmont.testtask.view.core.AbstractView;
import com.haulmont.testtask.view.core.Column;
import com.haulmont.testtask.view.core.Page;
import com.sun.istack.NotNull;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import java.util.Arrays;
import java.util.List;

public class DoctorView extends AbstractView<DoctorEntity> {

    public DoctorView() {
        super(DoctorEntity.class);
    }

    @Override
    protected void inject(Injector injector) {
        super.inject(injector);
        repository = injector.getInstance(Key.get(new TypeLiteral<Repository<DoctorEntity>>() {}));
        controller = injector.getInstance(Key.get(new TypeLiteral<Controller<DoctorEntity>>() {}));
    }

    @Override
    protected Page getPage() {
        return Page.DOCTORS;
    }

    @Override
    protected List<Column> getColumns() {
        return Arrays.asList(
                new Column("id", "Идентификатор", true),
                new Column("prescriptions", "Рецепты", true),
                new Column("lastName", "Фамилия", false),
                new Column("firstName", "Имя", false),
                new Column("middleName", "Отчество", false),
                new Column("specialization", "Отделение", false)
        );
    }

    @NotNull
    @Override
    protected List<Button> getAdvancedButtons() {
        Button statisticsButton = new Button("Статистика", FontAwesome.REORDER);
        statisticsButton.addClickListener(e -> {
            StatisticsModalView modalView = new StatisticsModalView();
            modalView.setDoctors(repository.getAll());
            modalView.setModal(true);
            getUI().addWindow(modalView);
        });
        return Arrays.asList(statisticsButton);
    }

    @Override
    protected void onEdit(DoctorEntity entity) {
        DoctorModalView modalView = new DoctorModalView(entity);
        modalView.setOnOkListener(doctor -> controller.update(doctor));
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }

    @Override
    protected void onAdd() {
        DoctorModalView modalView = new DoctorModalView();
        modalView.setOnOkListener(doctor -> controller.add(doctor));
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }
}
