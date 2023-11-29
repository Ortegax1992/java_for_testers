package tests;

import model.ContactsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactsRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactsData());
        }
        int contactsCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount - 1, newContactsCount);
    }
}
