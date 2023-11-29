package tests;

import model.ContactsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    public static List<ContactsData> contactsProvider() {
        var result = new ArrayList<ContactsData>();
        for (var firstname : List.of("", "John")) {
            for (var middlename : List.of("", "Smit")) {
                for (var lastname : List.of("", "Robert")) {
                    for (var nickname : List.of("", "Spitfire")) {
                        for (var title : List.of("", "Man")) {
                                result.add(new ContactsData(firstname, middlename, lastname, nickname, title));
                            }
                        }
                    }
                }
            }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactsData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactsProvider")
    public void canCreateMultipleContact(ContactsData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);
    }

    public static List<ContactsData> negativeContactsProvider() {
        var result = new ArrayList<ContactsData>(List.of(new ContactsData("John'", "Smit", "Robert", "Spitfire", "Man")));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeContactsProvider")
    public void canNotCreateContact(ContactsData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount, newContactsCount);
    }
}
