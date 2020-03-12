package com.haulmont.testtask.view.page.prescription;

import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.model.entity.PrescriptionEntity;
import com.haulmont.testtask.model.entity.Priority;
import com.haulmont.testtask.view.core.AbstractModalView;
import com.vaadin.data.Validatable;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.util.*;
import java.util.Calendar;

public class PrescriptionModalView extends AbstractModalView<PrescriptionEntity> {

    private long id;
    private ComboBox doctorComboBox;
    private ComboBox patientComboBox;
    private TextArea titleTextField;
    private ComboBox priorityComboBox;
    private DateField creationDateBox;
    private TextField validityTextField;

    public PrescriptionModalView() {
        super(null);
    }

    public PrescriptionModalView(PrescriptionEntity prescription) {
        super(prescription);
    }

    @Override
    protected void initFields(VerticalLayout layout) {
        doctorComboBox = new ComboBox("Врач");
        doctorComboBox.setFilteringMode(FilteringMode.CONTAINS);
        doctorComboBox.setNullSelectionAllowed(false);
        doctorComboBox.setRequired(true);
        doctorComboBox.setRequiredError("Выберите врача");
        doctorComboBox.setValidationVisible(false);
        doctorComboBox.setPageLength(5);
        doctorComboBox.setWidth("100%");
        layout.addComponent(doctorComboBox);

        patientComboBox = new ComboBox("Пациент");
        patientComboBox.setFilteringMode(FilteringMode.CONTAINS);
        patientComboBox.setNullSelectionAllowed(false);
        patientComboBox.setRequiredError("Выберите пациента");
        patientComboBox.setValidationVisible(false);
        patientComboBox.setRequired(true);
        patientComboBox.setPageLength(5);
        patientComboBox.setWidth("100%");
        layout.addComponent(patientComboBox);

        titleTextField = new TextArea("Описание");
        titleTextField.setRows(5);
        titleTextField.addValidator(new StringLengthValidator(
                "Необходимо заполнить описание (не более 500 знаков)",
                1,
                500,
                false
        ));
        titleTextField.setRequired(true);
        titleTextField.setRequiredError("Необходимо заполнить описание (не более 500 знаков)");
        titleTextField.setValidationVisible(false);
        titleTextField.setMaxLength(500);
        titleTextField.setWidth("100%");
        layout.addComponent(titleTextField);

        priorityComboBox = new ComboBox("Приоритет");
        priorityComboBox.addItems(Arrays.asList(Priority.values()));
        priorityComboBox.setTextInputAllowed(false);
        priorityComboBox.setNullSelectionAllowed(false);
        priorityComboBox.setRequired(true);
        priorityComboBox.setRequiredError("Выберите приоритет");
        priorityComboBox.setValidationVisible(false);
        priorityComboBox.setWidth("100%");
        layout.addComponent(priorityComboBox);

        creationDateBox = new DateField("Дата создания");
        Date minDate = new GregorianCalendar(0, Calendar.JANUARY, 1).getTime();
        Date maxDate = new GregorianCalendar(3000, Calendar.JANUARY, 1).getTime();
        creationDateBox.addValidator(new DateRangeValidator(
                "Введите дату создания в формате дд.мм.гггг",
                minDate,
                maxDate,
                Resolution.DAY
        ));
        creationDateBox.setRequired(true);
        creationDateBox.setRequiredError("Введите дату создания в формате дд.мм.гггг");
        creationDateBox.setValidationVisible(false);
        creationDateBox.setDateFormat("dd.MM.yyyy");
        creationDateBox.setRangeStart(minDate);
        creationDateBox.setRangeEnd(maxDate);
        creationDateBox.setParseErrorMessage("Необходимо ввести дату в формате дд.мм.гггг");
        creationDateBox.setWidth("100%");
        layout.addComponent(creationDateBox);

        validityTextField = new TextField("Срок действия, дней");
        validityTextField.setConverter(new StringToIntegerConverter());
        validityTextField.setNullRepresentation("");
        validityTextField.setImmediate(true);
        validityTextField.setRequired(true);
        validityTextField.setRequiredError("Введите количество срок действия в диапазоне от 0 до 365 дней");
        validityTextField.setValidationVisible(false);
        validityTextField.addValidator(new IntegerRangeValidator(
                "Введите количество срок действия в диапазоне от 0 до 365 дней",
                1,
                365
        ));
        validityTextField.setMaxLength(3);
        validityTextField.setWidth("100%");
        layout.addComponent(validityTextField);
    }

    @Override
    protected void initButtons(VerticalLayout layout) {
        Button okButton = new Button("OK");
        okButton.addClickListener(event -> {
            if (isValid()) {
                PrescriptionEntity prescription = new PrescriptionEntity();
                prescription.setId(id);
                prescription.setDoctor((DoctorEntity) doctorComboBox.getValue());
                prescription.setPatient((PatientEntity) patientComboBox.getValue());
                prescription.setTitle(titleTextField.getValue());
                prescription.setPriority((Priority) priorityComboBox.getValue());
                prescription.setCreationDate(new java.sql.Date(creationDateBox.getValue().getTime()));
                prescription.setValidity(Short.parseShort(validityTextField.getValue()));
                if (listener != null) {
                    listener.onClick(prescription);
                }
                close();
            }
        });

        Button cancelButton = new Button("ЗАКРЫТЬ");
        cancelButton.addClickListener(event -> close());

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setMargin(true);
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponents(okButton, cancelButton);

        layout.addComponent(buttonsLayout);
        layout.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_CENTER);
    }

    @Override
    protected void setValue(PrescriptionEntity prescription) {
        if (prescription == null) {
            setCaption("Добавить врача");
            return;
        }
        setCaption("Изменить данные врача");
        id = prescription.getId();
        doctorComboBox.select(prescription.getDoctor());
        patientComboBox.select(prescription.getPatient());
        titleTextField.setValue(prescription.getTitle());
        priorityComboBox.select(prescription.getPriority());
        creationDateBox.setValue(prescription.getCreationDate());
        validityTextField.setValue(String.valueOf(prescription.getValidity()));
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        doctorComboBox.addItems(doctors);
    }

    public void setPatients(List<PatientEntity> patients) {
        patientComboBox.addItems(patients);
    }

    @Override
    protected boolean isValid() {
        List<Validatable> fields = Arrays.asList(
                titleTextField,
                creationDateBox,
                validityTextField
        );
        return fields.stream().allMatch(Validatable::isValid);
    }

}
