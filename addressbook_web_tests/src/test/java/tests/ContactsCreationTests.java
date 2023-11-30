package tests;

import model.ContactsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    public static List<ContactsData> contactsProvider() {
        var result = new ArrayList<ContactsData>();
        for (var firstname : List.of("", "John")) {
            for (var middlename: List.of("", "middle")) {
            for (var lastname : List.of("", "Robert")) {
                result.add(new ContactsData().withFirstname(firstname).withMiddlename(middlename).withLastname(lastname));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactsData().withFirstname(randomString(i * 10)).withLastname(randomString(i * 10)).withMiddlename(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactsProvider")
    public void canCreateMultipleContact(ContactsData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactsData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withFirstname(contact.firstname()).withMiddlename("").withLastname(contact.lastname()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactsData> negativeContactsProvider() {
        var result = new ArrayList<ContactsData>(List.of(new ContactsData("", "Smit'", "middle", "Robert")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactsProvider")
    public void canNotCreateContact(ContactsData contact) {
        var oldContact = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContact = app.contacts().getList();
        Assertions.assertEquals(newContact, oldContact);
    }
}
