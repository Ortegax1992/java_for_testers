package manager;

import model.ContactsData;
import org.openqa.selenium.By;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactsData address) {
        addContact();
        fillContactsForm(address);
        submitCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHomePage();
        selectAlias();
        removeSelectContact();
        returnToHomePage();
    }

    private void removeSelectContact() {
        click(By.xpath("//input[contains(@value,'Delete')]"));
        manager.driver.switchTo().alert().accept();
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void fillContactsForm(ContactsData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("company"), contact.company());
        type(By.name("title"), contact.title());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());
        type(By.name("address2"), contact.address2());
        type(By.name("phone2"), contact.phone2());
        type(By.name("notes"), contact.notes());
    }

    private void addContact() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

}
