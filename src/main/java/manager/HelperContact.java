package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{
    Logger logger = LoggerFactory.getLogger(HelperContact.class);
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactAllForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
        click(By.xpath("//button/b"));
    }
    public void saveContactWithTab() {
        WebElement element = wd.findElement(By.cssSelector(".add_form__2rsm2 button"));
        element.sendKeys(Keys.TAB);
        pause(500);
        element.sendKeys(Keys.ENTER);

    }


    public boolean isContactAddedByName(String name) {
        boolean res = false;
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for(WebElement element: list){
            if(element.getText().equals(name))
                res = true;
        }
        return res;

    }

    public boolean isContactAddedByPhone(String phone) {
        boolean res = false;
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement element: list){
            if(element.getText().equals(phone))
                res = true;
        }
        return res;

    }

    public void fillContactRequiredForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
    }

    public boolean isAddPageStillDisplayed() {
        return  wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }


    public int removeOneContact() {
        int cnt = countContacts();
        if(cnt>0)
            removeContact();
        int cntAfter = countContacts();
        return cnt - cntAfter;
        
    }

    private void removeContact() {
        logger.info("Remove a contact...");
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(500);
    }

    public int countContacts() {
        int cnt = wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        logger.info("Contact count : "+ cnt);
        return cnt;
    }

    public void removeAllContacts() {
        //        List <WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        //        for (int i = 0; i < list.size(); i++) {
        //            click(By.cssSelector(".contact-item_card__2SOIM"));
        //            click(By.xpath("//button[text()='Remove']"));
        //            pause(500);
        //        }

        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeContact();
        }
    }

    public boolean isNoContactHere() {
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk h1")),"No Contacts here!" ));
    }

    public void providerOfContacts() {
        int cnt = countContacts();
        if(cnt<3)
            for(int i=0; i<3-cnt; i++)
                addNewContact();

    }

    private void addNewContact() {
        logger.info("Add new contact...");
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
        logger.info("Add a new contact: " + contact.toString());

        openContactForm();
        fillContactAllForm(contact);
        saveContactWithTab();
        pause(500);
    }
}
