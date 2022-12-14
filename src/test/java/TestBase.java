import manager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static AppManager app = new AppManager(System.getProperty("browser", Browser.CHROME.browserName()));


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void getTestName(Method m){
        logger.info("START TEST WITH NAME: "+ m.getName());
    }
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.Stop();

    }

}
