package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void registration(String username, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        click(By.linkText("Proceed"));
    }

    public void followTheLinkAndRegister(String url, String username, String password){
        manager.driver().get(url);
        type(By.name("realname"), username);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.xpath("//button[@type=\'submit\']"));
    }
}
