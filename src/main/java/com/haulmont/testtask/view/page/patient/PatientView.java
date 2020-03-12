package com.haulmont.testtask.view.page.patient;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.model.repository.Repository;
import com.haulmont.testtask.view.core.AbstractView;
import com.haulmont.testtask.view.core.Column;
import com.haulmont.testtask.view.core.Page;

import java.util.Arrays;
import java.util.List;

public class PatientView extends AbstractView<PatientEntity> {

    public PatientView() {
        super(PatientEntity.class);
    }

    @Override
    protected void inject(Injector injector) {
        super.inject(injector);
        repository = injector.getInstance(Key.get(new TypeLiteral<Repository<PatientEntity>>() {}));
        controller = injector.getInstance(Key.get(new TypeLiteral<Controller<PatientEntity>>() {}));
    }

    @Override
    protected Page getPage() {
        return Page.PATIENTS;
    }

    @Override
    protected List<Column> getColumns() {
        return Arrays.asList(
                new Column("id", "Идентификатор", true),
                new Column("lastName", "Фамилия", false),
                new Column("firstName", "Имя", false),
                new Column("middleName", "Отчество", false),
                new Column("phoneNumber", "Номер телефона", false)
        );
    }

    @Override
    protected void onEdit(PatientEntity entity) {
        PatientModalView modalView = new PatientModalView(entity);
        modalView.setOnOkListener(patient -> controller.update(patient));
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }

    @Override
    protected void onAdd() {
        PatientModalView modalView = new PatientModalView();
        modalView.setOnOkListener(patient -> controller.add(patient));
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }
}
