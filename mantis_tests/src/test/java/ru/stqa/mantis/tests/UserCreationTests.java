package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserCreationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        String password = "password";
        user = app.developerMail().addUser();
        String email = String.format("%s@developermail.com", user.name());
//        app.jamesApi().addUser(email, password);



//        app.session().registration(user, email);
//        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//        var text = messages.get(0).content();
//        var pattern = Pattern.compile("http://\\S*");
//        var matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            var url = text.substring(matcher.start(), matcher.end());
//            System.out.println(url);
//            app.driver().get(url);
//        }
//        var name = "name";
//        app.session().followTheLinkAndRegister(name, password);
//        app.http().login(user, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser(){
        app.developerMail().deleteUser(user);
    }
}
