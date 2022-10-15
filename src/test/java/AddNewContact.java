import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContact extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("MarinaShp@gmail.com").withPassword("Mmarina12345$"));
    }
    @Test
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
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactAllForm(contact);
        app.getHelperContact().saveContactWithTab();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
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
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactRequiredForm(contact);
        app.getHelperContact().saveContactWithTab();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }



    @Test
    public void addNewContactWrongName(){

        Contact contact = Contact.builder()
                .lastName("Snow")
                .phone("34343452143568")
                .email("zoa@mail.com")
                .address("Haifa")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactRequiredForm(contact);
        app.getHelperContact().saveContactWithTab();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

}
