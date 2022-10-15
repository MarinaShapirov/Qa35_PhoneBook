import manager.HelperUser;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345$");
        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccess() {
        logger.info("Test starts with name ---> loginSuccess");

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm("MarinaShp@gmail.com", "Mmarina12345$");
        logger.info("User login (MarinaShp@gmail.com, Mmarina12345$)");

        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert passed");

    }

    @Test
    public void loginNegative_WrongEmailFormat() {
        User user = new User().withEmail("MarinaShpgmail.com").withPassword("Mmarina12345$");

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());

    }

    @Test
    public void loginNegative_WrongPswFormat() {
        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345");

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());


    }
}
