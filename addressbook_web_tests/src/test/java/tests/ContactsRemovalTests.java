package tests;

import model.ContactsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactsRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactsData("","John","","Robert"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void  canRemoveAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactsData("","John","","Robert"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactsCount());
    }
}
