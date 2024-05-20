package com.pms.application.views.personform;

import com.pms.application.data.SamplePerson;
import com.pms.application.services.SamplePersonService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Person Form")
@Menu(icon = "line-awesome/svg/user.svg", order = 3)
@Route(value = "person-form")
public class PersonFormView extends Composite<VerticalLayout> {

    private final SamplePersonService personService;

    private TextField firstName;
    private TextField lastName;
    private DatePicker birthday;
    private TextField phoneNumber;
    private EmailField email;
    private TextField occupation;

    public PersonFormView(SamplePersonService personService) {
        this.personService = personService;

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        firstName = new TextField();
        lastName = new TextField();
        birthday = new DatePicker();
        phoneNumber = new TextField();
        email = new EmailField();
        occupation = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Personal Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        firstName.setLabel("First Name");
        lastName.setLabel("Last Name");
        birthday.setLabel("Birthday");
        phoneNumber.setLabel("Phone Number");
        email.setLabel("Email");
        occupation.setLabel("Occupation");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");

        // Save button click listener
        buttonPrimary.addClickListener(e -> savePerson());

        // Cancel button click listener
        buttonSecondary.addClickListener(e -> clearForm());

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(firstName);
        formLayout2Col.add(lastName);
        formLayout2Col.add(birthday);
        formLayout2Col.add(phoneNumber);
        formLayout2Col.add(email);
        formLayout2Col.add(occupation);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
    }

    private void savePerson() {
        SamplePerson person = new SamplePerson();
        person.setFirstName(firstName.getValue());
        person.setLastName(lastName.getValue());
        person.setDateOfBirth(birthday.getValue());
        person.setPhone(phoneNumber.getValue());
        person.setEmail(email.getValue());
        person.setOccupation(occupation.getValue());

        personService.savePerson(person);
        Notification.show("Person saved!");
        clearForm();
    }

    private void clearForm() {
        firstName.clear();
        lastName.clear();
        birthday.clear();
        phoneNumber.clear();
        email.clear();
        occupation.clear();
    }
}
