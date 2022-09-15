import manager.HelperUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void loginSuccess() {
        app.getHelperUser().openRegstrForm();
        app.getHelperUser().fillLoginRegstrForm("MarinaShp@gmail.com", "Mmarina12345$");
        app.getHelperUser().submitLogin();
    }

    @Test
    public void loginNegative_WrongEmailFormat() {

    }

    @Test
    public void loginNegative_WrongPswFormat() {

    }
}
