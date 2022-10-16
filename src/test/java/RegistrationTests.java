import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
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
    public void regstrSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withEmail("MarinaShp"+ i+ "@gmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: isLogged");
    }

    @Test
    public void regstrNegative_WrongEmail() {
        User user = new User().withEmail("MarinaShpgmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();

        Assert.assertFalse(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: Not logged");
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        logger.info("ASSERT passed: isAlertAppear");
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());
        logger.info("ASSERT passed: isWrongFormatErrAppear");

    }

    @Test
    public void regstrNegative_WrongPsw() {
        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mm");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();

        Assert.assertFalse(app.getHelperUser().isLogged());
        logger.info("ASSERT passed: Not logged");
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        logger.info("ASSERT passed: isAlertAppear");
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());
        logger.info("ASSERT passed: isWrongFormatErrAppear");

    }
}
