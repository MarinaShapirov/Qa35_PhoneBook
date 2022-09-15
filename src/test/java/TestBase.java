import manager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.List;

public class TestBase {

    static AppManager app = new AppManager();

    @BeforeSuite
    public void setUp() {
        app.Init();
    }

    @AfterSuite
    public void tearDown() {
        app.Stop();

    }

}
