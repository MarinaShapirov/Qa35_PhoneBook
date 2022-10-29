import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContact extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        logger.info("Authorization check");
        if(!app.getHelperUser().isLogged()) {
            User user = new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345$");
            logger.info("User login: " + user.getEmail()+ " " + user.getPassword());
            app.getHelperUser().login(user);

            }
        else
            logger.info("Authorized.");
    }
    @Test()
    public void addNewContactSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Name"+i)
                .lastName("LastName")
                .phone("3434345"+i)
                .email("name"+i+"@mail.com")
                .address("Address")
                .description("Description")
                .build();
        logger.info("Add new contact: " + contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactAllForm(contact);
        app.getHelperContact().saveContactWithTab();
        //
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        logger.info("ASSERT passed: isContactAddedByName");
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("ASSERT passed: isContactAddedByPhone");
    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Name"+i)
                .lastName("LastName")
                .phone("3434345"+i)
                .email("name"+i+"@mail.com")
                .address("Address")
                .build();
        logger.info("Add new contact: " + contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactRequiredForm(contact);
        app.getHelperContact().saveContactWithTab();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        logger.info("ASSERT passed: isContactAddedByName");
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("ASSERT passed: isContactAddedByPhone");
    }



    @Test(groups = {"smoke_group"})
    public void addNewContactWrongName(){

        Contact contact = Contact.builder()
                .lastName("Snow")
                .phone("34343452143568")
                .email("zoa@mail.com")
                .address("Haifa")
                .build();
        logger.info("Add new contact: " + contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactRequiredForm(contact);
        app.getHelperContact().saveContactWithTab();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("ASSERT passed: isAddPageStillDisplayed");

    }

}
