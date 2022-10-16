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
        logger.info("Authorization check");
        if (app.getHelperUser().isLogged()) {
            logger.info("Authorized. Log out...");
            app.getHelperUser().logOut();
            }
        else
            logger.info("Not authorized");
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: isLogged");
    }

    @Test
    public void loginSuccess() {
        logger.info("User login (MarinaShp@gmail.com, Mmarina12345$)");
        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm("MarinaShp@gmail.com", "Mmarina12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: isLogged");

    }

    @Test
    public void loginNegative_WrongEmailFormat() {
        User user = new User().withEmail("MarinaShpgmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertFalse(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: Not logged");
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        logger.info("ASSERT passed: isAlertAppear");
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());
        logger.info("ASSERT passed: isWrongFormatErrAppear");

    }

    @Test
    public void loginNegative_WrongPswFormat() {

        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertFalse(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: Not logged");
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        logger.info("ASSERT passed: isAlertAppear");
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());
        logger.info("ASSERT passed: isWrongFormatErrAppear");


    }
}
