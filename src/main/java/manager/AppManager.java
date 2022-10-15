package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AppManager {
    WebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;

    public void Init(){
        wd = new ChromeDriver();
        //config
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/");

        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);

    }
    public void Stop(){
        //wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }
    public HelperContact getHelperContact(){ return helperContact;}
}
