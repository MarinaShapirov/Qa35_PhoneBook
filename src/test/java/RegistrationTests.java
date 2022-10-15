import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    @Test
    public void regstrSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withEmail("MarinaShp"+ i+ "@gmail.com").withPassword("Mmarina12345$");
        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void regstrNegative_WrongEmail() {
        User user = new User().withEmail("MarinaShpgmail.com").withPassword("Mmarina12345$");

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());

    }

    @Test
    public void regstrNegative_WrongPsw() {
        User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mm");

        app.getHelperUser().openLoginRegstrForm();
        app.getHelperUser().fillLoginRegstrForm(user);
        app.getHelperUser().submitRegstr();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertAppear());
        Assert.assertTrue(app.getHelperUser().isWrongFormatErr());

    }
}
