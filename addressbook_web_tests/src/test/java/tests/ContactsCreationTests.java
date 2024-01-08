package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactsData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    public static List<ContactsData> contactsProvider() throws IOException {
        var result = new ArrayList<ContactsData>();
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactsData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactsData> singleRandomGroup() throws IOException {
        return List.of(new ContactsData()
                .withFirstname(CommonFunctions.randomString(10))
                .withMiddlename(CommonFunctions.randomString(20))
                .withLastname(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("contactsProvider")
    public void canCreateMultipleContact(ContactsData contact) {
        var oldContacts = app.hbm().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactsList();
        Comparator<ContactsData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
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
        var oldContact = app.hbm().getContactsList();
        app.contacts().createContact(contact);
        var newContact = app.hbm().getContactsList();
        Assertions.assertEquals(newContact, oldContact);
    }
}
