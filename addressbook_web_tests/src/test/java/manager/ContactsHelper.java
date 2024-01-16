package manager;

import io.qameta.allure.Step;
import model.ContactsData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    @Step
    public void createContact(ContactsData contact) {
        openHomePage();
        addContact();
        fillContactsForm(contact);
        submitCreation();
        returnToHomePage();
    }

    @Step
    public void createContact(ContactsData contact, GroupData group) {
        openHomePage();
        addContact();
        fillContactsForm(contact);
        selectGroup(group);
        submitCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupToAdd(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroupFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void addContactInGroup() {
        click(By.xpath("//input[@name='add']"));
    }

    private void removeSelectedContactFromGroup() {
        click(By.name("remove"));
    }

    @Step
    public void removeContact(ContactsData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectContact();
        openHomePage();
    }

    @Step
    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectContact();
        openHomePage();
    }

    @Step
    public void modifyContact(ContactsData contact, ContactsData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactsForm(modifiedContact);
        submitContactModification();
        openHomePage();
    }

    private void initContactModification(ContactsData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    protected void submitContactModification() {
        click(By.name("update"));
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
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("phone2"), contact.secondary());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email2());
    }

    private void addContact() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    @Step
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

    @Step
    public void addContactInGroup(GroupData group, ContactsData contact) {
        openHomePage();
        selectGroupToAdd(group);
        selectContact(contact);
        addContactInGroup();
        openHomePage();
    }

    @Step
    public void removeContactFromGroup(ContactsData contact, GroupData group) {
        openHomePage();
        selectGroupFilter(group);
        selectContact(contact);
        removeSelectedContactFromGroup();
    }

    public String getPhones(ContactsData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    @Step
    public Map<String, String> getPhones() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    @Step
    public Map<String, String> getAddresses() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public Object getEmails() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }
}