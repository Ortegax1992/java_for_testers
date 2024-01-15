package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUserRespApi(){
        String username = CommonFunctions.randomString(8);
        String email = String.format("%s@localhost", username);
        String password = "password";
        app.jamesApi().addUser(email,password);
        app.rest().startRegistration(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(30));

        var url = CommonFunctions.extractUrl(messages.get(0).content());

        app.session().followTheLinkAndRegister(url, username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterUser() {
        String username = CommonFunctions.randomString(8);
        String email = String.format("%s@localhost", username);
        String password = "password";
        app.jamesCli().addUser(email, password);
        app.session().registration(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
            app.driver().get(url);
        }
        var name = "name";
        var url = CommonFunctions.extractUrl(text);
        app.session().followTheLinkAndRegister(url, name, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
