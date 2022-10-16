import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase{
    @BeforeMethod
    public void preCondition(){
        logger.info("Authorization check");
        if(!app.getHelperUser().isLogged()) {
            User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345$");
            logger.info("User login: " + user.getEmail()+ " " + user.getPassword());
            app.getHelperUser().login(user);
        }
        else
            logger.info("Authorized.");
        app.getHelperContact().providerOfContacts();
    }

    @Test
    public void removeFirstContact(){
        //int cntBefore = app.getHelperContact().countContacts();
        //app.getHelperContact().removeOneContact();
        //int cntAfter = app.getHelperContact().countContacts();
        //Assert.assertEquals(cntAfter-cntBefore, 1);
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }

    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperContact().isNoContactHere());
    }
}
