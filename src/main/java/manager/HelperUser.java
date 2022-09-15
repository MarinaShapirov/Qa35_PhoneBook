package manager;

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
        WebElement inpEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
        inpEmail.click();
        inpEmail.clear();
        inpEmail.sendKeys(email);

        WebElement inpPsw = wd.findElement(By.xpath("//input[@placeholder='Password']"));
        inpPsw.click();
        inpPsw.click();
        inpPsw.sendKeys(password);
    }

    public void submitLogin(){
        //login submit
        WebElement loginBtn = wd.findElement(By.xpath("//*[text()=' Login']"));
        loginBtn.click();
    }

}
