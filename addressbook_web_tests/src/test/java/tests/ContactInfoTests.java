package tests;

import common.CommonFunctions;
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
        var expected = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);

        expected = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var addresses = app.contacts().getAddresses();
        Assertions.assertEquals(expected, addresses);

        expected = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expected, emails);
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
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddresses() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactsData()
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withAddress("Prospect mira"));
        }
        var contacts = app.hbm().getContactsList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactsData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var addresses = app.contacts().getAddresses();
        Assertions.assertEquals(expected, addresses);
    }
}

