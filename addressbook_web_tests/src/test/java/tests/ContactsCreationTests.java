package tests;

import model.ContactsData;
import org.junit.jupiter.api.Test;

public class ContactsCreationTests extends TestBase {

    @Test
    public void canCreateNewContact() {
        app.contacts().createContact(new ContactsData("John", "Smit", "Robert", "Spitfire", "Man", "Microsoft",
                "USA", "84993503484", "89852223344", "88002000600", "03-1234-5678", "John@outlook.com",
                "Smit_robert@outlook.com", "Robert_smit@outlook.com", "www.microsoft.com", "LA", "9119191", "No comments"));
    }

    @Test
    public void canCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactsData());
    }
}
