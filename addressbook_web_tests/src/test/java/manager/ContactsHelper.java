package manager;

import model.ContactsData;
import org.openqa.selenium.By;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactsData contact) {
        addContact();
        fillContactsForm(contact);
        submitCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHomePage();
        selectAlias();
        removeSelectContact();
    }

    private void removeSelectContact() {
        click(By.xpath("//input[contains(@value,'Delete')]"));
        manager.driver.switchTo().alert().accept();
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void fillContactsForm(ContactsData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
      }

    private void addContact() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
