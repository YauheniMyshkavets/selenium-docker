package tests.tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected RemoteWebDriver driver;

    @BeforeTest (alwaysRun = true)
    public void setUp(ITestContext ctx) {

        DesiredCapabilities dc;
        String SERVER_URL = "http://localhost:4444/wd/hub";

        if(System.getProperty("BROWSER") != null &&
            System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
            dc = DesiredCapabilities.chrome();
        }

        //to add name to zalenium test (name from suite)
        String testName = ctx.getCurrentXmlTest().getName();
        dc.setCapability("name", testName);
        try {
            driver = new RemoteWebDriver(new URL(SERVER_URL), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
