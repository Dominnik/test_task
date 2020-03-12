package com.haulmont.testtask.view.page.patient;

import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.view.core.AbstractModalView;
import com.vaadin.data.Validatable;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

public class PatientModalView extends AbstractModalView<PatientEntity> {

    private long id;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField middleNameTextField;
    private TextField phoneTextField;

    public PatientModalView() {
        super(null);
    }

    public PatientModalView(PatientEntity patient) {
        super(patient);
    }

    @Override
    protected void initFields(VerticalLayout layout) {
        lastNameTextField = new TextField("Фамилия");
        lastNameTextField.addValidator(new StringLengthValidator(
                "Введите фамилию (не более 50 знаков)",
                1,
                50,
                false
        ));
        lastNameTextField.setRequired(true);
        lastNameTextField.setRequiredError("Введите фамилию (не более 50 знаков)");
        lastNameTextField.setValidationVisible(false);
        lastNameTextField.setMaxLength(50);
        lastNameTextField.setWidth("100%");
        layout.addComponent(lastNameTextField);

        firstNameTextField = new TextField("Имя");
        firstNameTextField.addValidator(new StringLengthValidator(
                "Введите имя (не более 50 знаков)",
                1,
                50,
                false
        ));
        firstNameTextField.setRequired(true);
        firstNameTextField.setRequiredError("Введите имя (не более 50 знаков)");
        firstNameTextField.setValidationVisible(false);
        firstNameTextField.setMaxLength(50);
        firstNameTextField.setWidth("100%");
        layout.addComponent(firstNameTextField);

        middleNameTextField = new TextField("Отчество");
        middleNameTextField.addValidator(new StringLengthValidator(
                "Введите отчество (не более 50 знаков)",
                0,
                50,
                true
        ));
        middleNameTextField.setRequiredError("Введите отчество (не более 50 знаков)");
        middleNameTextField.setValidationVisible(false);
        middleNameTextField.setMaxLength(50);
        middleNameTextField.setWidth("100%");
        layout.addComponent(middleNameTextField);

        phoneTextField = new TextField("Номер телефона");
        phoneTextField.addValidator(new RegexpValidator(
                "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
                "Неверный формат номера"
        ));
        phoneTextField.setMaxLength(50);
        phoneTextField.setWidth("100%");
        layout.addComponent(phoneTextField);
    }

    @Override
    protected void initButtons(VerticalLayout layout) {
        Button okButton = new Button("OK");
        okButton.addClickListener(event -> {
            if (isValid()) {
                PatientEntity patient = new PatientEntity();
                patient.setId(id);
                patient.setFirstName(firstNameTextField.getValue());
                patient.setLastName(lastNameTextField.getValue());
                patient.setMiddleName(middleNameTextField.getValue());
                patient.setPhoneNumber(phoneTextField.getValue());
                if (listener != null) {
                    listener.onClick(patient);
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
    protected void setValue(PatientEntity patient) {
        if (patient == null) {
            setCaption("Добавить пациента");
            return;
        }
        setCaption("Изменить данные пациента");
        id = patient.getId();
        firstNameTextField.setValue(patient.getFirstName());
        lastNameTextField.setValue(patient.getLastName());
        middleNameTextField.setValue(patient.getMiddleName());
        phoneTextField.setValue(patient.getPhoneNumber());
    }

    @Override
    protected boolean isValid() {
        List<Validatable> fields = Arrays.asList(
                firstNameTextField,
                lastNameTextField,
                middleNameTextField,
                phoneTextField
        );
        return fields.stream().allMatch(Validatable::isValid);
    }

}
