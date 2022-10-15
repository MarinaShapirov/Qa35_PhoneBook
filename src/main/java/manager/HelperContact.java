package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase{
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


}
