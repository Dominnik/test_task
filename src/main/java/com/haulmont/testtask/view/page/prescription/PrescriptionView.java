package com.haulmont.testtask.view.page.prescription;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.model.entity.PrescriptionEntity;
import com.haulmont.testtask.model.entity.Priority;
import com.haulmont.testtask.model.repository.Repository;
import com.haulmont.testtask.view.core.AbstractView;
import com.haulmont.testtask.view.core.Column;
import com.haulmont.testtask.view.core.Page;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

public class PrescriptionView extends AbstractView<PrescriptionEntity> {

    private Repository<DoctorEntity> doctorRepository;
    private Repository<PatientEntity> patientRepository;

    public PrescriptionView() {
        super(PrescriptionEntity.class);
    }

    @Override
    public void initTopComponents() {
        initFilters();
    }

    @Override
    protected void inject(Injector injector) {
        super.inject(injector);
        repository = injector.getInstance(Key.get(new TypeLiteral<Repository<PrescriptionEntity>>() {
        }));
        doctorRepository = injector.getInstance(Key.get(new TypeLiteral<Repository<DoctorEntity>>() {
        }));
        patientRepository = injector.getInstance(Key.get(new TypeLiteral<Repository<PatientEntity>>() {
        }));
        controller = injector.getInstance(Key.get(new TypeLiteral<Controller<PrescriptionEntity>>() {
        }));
    }

    @Override
    protected Page getPage() {
        return Page.PRESCRIPTIONS;
    }

    @Override
    protected List<Column> getColumns() {
        return Arrays.asList(
                new Column("id", "Идентификатор", true),
                new Column("title", "Описание", false),
                new Column("patient", "Пациент", false),
                new Column("doctor", "Врач", false),
                new Column("creationDate", "Дата создания", false),
                new Column("validity", "Срок действия", false),
                new Column("priority", "Приоритет", false)
        );
    }

    private void initFilters() {
        HorizontalLayout filterLayout = new HorizontalLayout();
        filterLayout.setSizeFull();
        filterLayout.setMargin(true);
        filterLayout.setSpacing(true);
        Panel filterPanel = new Panel("Фильтр");
        filterPanel.setContent(filterLayout);

        ComboBox patientFilterBox = new ComboBox("Выберите пациента:");
        patientFilterBox.setSizeFull();
        patientFilterBox.setFilteringMode(FilteringMode.CONTAINS);
        getTableItems().forEach(item -> patientFilterBox.addItem(item.getPatient()));
        patientFilterBox.addValueChangeListener(event -> {
            Object filter = patientFilterBox.getValue();
            applyFilter("patient", filter != null ? filter.toString() : "");
        });
        filterLayout.addComponent(patientFilterBox);

        ComboBox priorityFilterBox = new ComboBox("Выберите приоритет:");
        priorityFilterBox.setSizeFull();
        priorityFilterBox.addItems(Arrays.asList(Priority.values()));
        priorityFilterBox.setTextInputAllowed(false);
        priorityFilterBox.addValueChangeListener(event -> {
            Object filter = priorityFilterBox.getValue();
            applyFilter("priority", filter != null ? filter.toString() : "");
        });
        filterLayout.addComponent(priorityFilterBox);

        TextField titleFilterField = new TextField("Введите описание");
        titleFilterField.setSizeFull();
        titleFilterField.addTextChangeListener(event -> {
            String filter = event.getText();
            applyFilter("title", filter != null ? filter : "");
        });
        filterLayout.addComponent(titleFilterField);
        addComponents(filterPanel);
    }

    @Override
    protected void onEdit(PrescriptionEntity entity) {
        PrescriptionModalView modalView = new PrescriptionModalView();
        modalView.setOnOkListener(prescription -> controller.update(prescription));
        modalView.setDoctors(doctorRepository.getAll());
        modalView.setPatients(patientRepository.getAll());
        modalView.setValue(entity);
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }

    @Override
    protected void onAdd() {
        PrescriptionModalView modalView = new PrescriptionModalView();
        modalView.setOnOkListener(prescription -> controller.add(prescription));
        modalView.setDoctors(doctorRepository.getAll());
        modalView.setPatients(patientRepository.getAll());
        modalView.setModal(true);
        getUI().addWindow(modalView);
    }
}
