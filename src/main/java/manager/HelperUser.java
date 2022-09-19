package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        return list.size() > 0;
    }

    public void logOut() {
        wd.findElement(By.xpath("//button[text()='Sign Out']")).click();
    }

    public void openRegstrForm(){
        WebElement loginTab = wd.findElement(By.xpath("//a[@href = '/login']"));
        loginTab.click();
    }

    public void fillLoginRegstrForm(String email, String password ){
        /*WebElement inpEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
        inpEmail.click();
        inpEmail.clear();
        inpEmail.sendKeys(email);*/
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.xpath("//input[@placeholder='Password']"), password);
    }

    public void fillLoginRegstrForm(User user ){
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());
    }

    public void submitLogin(){
        //login submit
        WebElement loginBtn = wd.findElement(By.xpath("//*[text()=' Login']"));
        loginBtn.click();
    }

    public boolean isAlertAppear() {
        boolean res = false;
        Alert alert = wd.switchTo().alert();
        if (alert !=null)
            res = true;
        return res;
    }

    public boolean isWrongFormatErr() {

        Alert alert = wd.switchTo().alert();
        String errText = alert.getText();
        System.out.println(errText);
        //click OK btn on alert window
        alert.accept();

        return errText.contains("Wrong email or password format");
    }
}
