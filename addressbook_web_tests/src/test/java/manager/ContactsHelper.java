package manager;

import model.ContactsData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public void removeContact(ContactsData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectContact();
        openHomePage();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectContact();
        openHomePage();
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

    protected void selectContact(ContactsData contact) {
        click(By.cssSelector(String.format("input[value='%s'", contact.id())));
    }

    private void selectAllContacts() {
            click(By.xpath("//input[contains(@id,'MassCB')]"));
        }

    private void fillContactsForm(ContactsData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
    }

    private void addContact() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactsData> getList() {
        var contacts = new ArrayList<ContactsData>();
        var elements = manager.driver.findElements(By.xpath("//tr[@name='entry']"));
        for (var element : elements) {
            var firstname = element.findElement(By.xpath(".//td[3]")).getText();
            var lastname = element.findElement(By.xpath(".//td[2]")).getText();
            var checkbox = element.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactsData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}