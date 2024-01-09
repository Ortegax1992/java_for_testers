package tests;

import common.CommonFunctions;
import model.ContactsData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactsModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactsData("", "John", "", "Robert"));
        }
        var oldContacts = app.hbm().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactsData().withFirstname("modified firstname");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactsList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactsData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContactInGroup() {
        app.groups().removeAllGroups();
        app.contacts().removeAllContacts();
        app.hbm().createContact(new ContactsData("", "John", "", "Robert"));
        app.hbm().createGroup(new GroupData("", "group_name", "group_header", "group_footer"));
        var oldRelated = app.hbm().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        var contact = oldRelated.get(index);
        var group = app.hbm().getGroupList().get(0);
        var expectedContactListInGroup = new ArrayList<>(oldRelated);
        if (app.hbm().checkingContactInGroup(group, contact)) {
            app.contacts().removeContactFromGroup(contact, group);
        }
        app.contacts().addContactInGroup(group, contact);
        var newRelated = app.hbm().getContactsList();
        var newContactsListInGroup = new ArrayList<>(newRelated);
        Comparator<ContactsData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        expectedContactListInGroup.sort(compareById);
        newContactsListInGroup.sort(compareById);
        Assertions.assertEquals(expectedContactListInGroup, newContactsListInGroup);
    }

    @Test
    public void canRemoveContactFromGroup() {
        app.groups().removeAllGroups();
        app.contacts().removeAllContacts();
        app.hbm().createContact(new ContactsData("", "John", "", "Robert"));
        app.hbm().createGroup(new GroupData("", "group_name", "group_header", "group_footer"));
        var oldRelated = app.hbm().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        var contact = oldRelated.get(index);
        var group = app.hbm().getGroupList().get(0);
        var expectedContactListInGroup = new ArrayList<>(oldRelated);
        if (!app.hbm().checkingContactInGroup(group, contact)) {
            app.contacts().addContactInGroup(group, contact);
        }
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsList();
        var newContactsListInGroup = new ArrayList<>(newRelated);
        Comparator<ContactsData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        expectedContactListInGroup.sort(compareById);
        newContactsListInGroup.sort(compareById);
        Assertions.assertEquals(expectedContactListInGroup, newContactsListInGroup);
    }
}
