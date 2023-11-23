package tests;

import model.ContactsData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactsRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactsData());
        }
        app.contacts().removeContact();
    }
}
