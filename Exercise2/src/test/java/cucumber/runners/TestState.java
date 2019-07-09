package cucumber.runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestState {

    // The shared selenium web driver
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setupState() {
        System.out.println("This will run before the Scenario");
        System.setProperty("webdriver.chrome.driver", "src//webdriver//chrome//driver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public void teardownState() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
