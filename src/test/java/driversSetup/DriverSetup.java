package driversSetup;

import java.io.File;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class DriverSetup {

	static protected WebDriver driver;

    public static void waitUntilElementDisplayed(final WebElement e, int sec, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, sec);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    e.isDisplayed();
                    return true;
                }
                catch (Exception e ) {
                    return false;
                }
            }
        };
        wait.until(elementIsDisplayed);
        System.out.println(e.isDisplayed());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

	static String pathToDriver = Paths.get(System.getProperty("user.dir")).toAbsolutePath().toString() +
			File.separator + "src"+ File.separator + "test" + File.separator + "java"+ File.separator + "driversSetUp" + File.separator;
    public enum Browser {
        Chrome,
        IE,
        Firefox
    }
    
	public static WebDriver setupDriver(Browser browser, String driverFileName) {
		WebDriver driver = null;
		String osName = (System.getProperty("os.name").toLowerCase().contains("mac") ? "mac" : "windows");
		if(browser == Browser.Chrome) {
			if (osName.equals("windows")) {
				System.setProperty("webdriver.chrome.driver", pathToDriver + driverFileName);
			} else {
				System.setProperty("webdriver.chrome.driver", pathToDriver + driverFileName);
			}
			driver = new ChromeDriver();
		}
		
		else if(browser == Browser.Firefox) {
			//Add config
		}
		
		else if(browser == Browser.IE) {
			//Add config
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}


    @BeforeClass(alwaysRun = true)
    public void setupClass()
    {

    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass()
    {

    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest()
    {
        driver = DriverSetup.setupDriver(Browser.Chrome, "chromedriver");
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDownTest()
    {
        driver.quit();
    }
	
}
