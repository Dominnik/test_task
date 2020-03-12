package com.haulmont.testtask.view.page.doctor;

import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.view.core.AbstractModalView;
import com.vaadin.data.Validatable;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

public class DoctorModalView extends AbstractModalView<DoctorEntity> {

    private long id;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField middleNameTextField;
    private TextField specializationTextField;

    public DoctorModalView() {
        super(null);
    }

    public DoctorModalView(DoctorEntity doctor) {
        super(doctor);
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

        specializationTextField = new TextField("Отделение");
        specializationTextField.addValidator(new StringLengthValidator(
                "Введите отделение (не более 50 знаков)",
                1,
                50,
                false
        ));
        specializationTextField.setRequired(true);
        specializationTextField.setRequiredError("Введите отделение (не более 50 знаков)");
        specializationTextField.setValidationVisible(false);
        specializationTextField.setMaxLength(50);
        specializationTextField.setWidth("100%");
        layout.addComponent(specializationTextField);
    }

    @Override
    protected void initButtons(VerticalLayout layout) {
        Button okButton = new Button("OK");
        okButton.addClickListener(event -> {
            if (isValid()) {
                DoctorEntity doctor = new DoctorEntity();
                doctor.setId(id);
                doctor.setFirstName(firstNameTextField.getValue());
                doctor.setLastName(lastNameTextField.getValue());
                doctor.setMiddleName(middleNameTextField.getValue());
                doctor.setSpecialization(specializationTextField.getValue());
                if (listener != null) {
                    listener.onClick(doctor);
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
    protected void setValue(DoctorEntity doctor) {
        if (doctor == null) {
            setCaption("Добавить врача");
            return;
        }
        setCaption("Изменить данные врача");
        id = doctor.getId();
        firstNameTextField.setValue(doctor.getFirstName());
        lastNameTextField.setValue(doctor.getLastName());
        middleNameTextField.setValue(doctor.getMiddleName());
        specializationTextField.setValue(doctor.getSpecialization());
    }

    @Override
    protected boolean isValid() {
        List<Validatable> fields = Arrays.asList(
                firstNameTextField,
                lastNameTextField,
                middleNameTextField,
                specializationTextField
        );
        return fields.stream().allMatch(Validatable::isValid);
    }

}
