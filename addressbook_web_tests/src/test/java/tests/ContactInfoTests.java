package tests;

import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.ContactsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhonesEmailsAdresses() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactsData()
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withHome("2222")
                    .withMobile("33333")
                    .withWork("44444444")
                    .withSecondary("353535")
                    .withAddress("Prospect mira")
                    .withEmail("Nick@mail.com")
                    .withEmail2("Rock@mail.com")
                    .withEmail3("Ice@outlook.com")
            );
        }
        var contacts = app.hbm().getContactsList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();

        var expectedAdresses = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.address())
                        .collect(Collectors.joining("\n"))
        ));
        var addresses = app.contacts().getAddresses();

        var expectedEmails = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmails();
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(expectedPhones, phones);
            Assertions.assertEquals(expectedAdresses, addresses);
            Assertions.assertEquals(expectedEmails, emails);
        });
    }

    @Test
    void testEmail() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactsData()
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withEmail("Nick@mail.com")
                    .withEmail2("Rock@mail.com")
                    .withEmail3("Ice@outlook.com"));
        }
        var contacts = app.hbm().getContactsList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmails();
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(expected, emails);
        });
    }
}

