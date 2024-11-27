package issta.co.il;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    /**
     * Sets up the WebDriver for the specified browser before the test class is run.
     * --
     * This method is annotated with `@BeforeClass`, which means it will be executed once before
     * any test methods in the class are executed. It initializes the WebDriver based on the
     * browser passed as a parameter (defaulting to "Chrome").
     * --
     * It performs the following actions:
     * 1. Initializes the corresponding WebDriver (Chrome, Firefox, or Edge) based on the provided
     * browser name.
     * 2. Maximizes the browser window.
     * 3. Launches the main URL of the application using the `MainUrl` property from the configuration.
     * 4. Deletes all cookies from the browser session to ensure a clean slate for the test.
     *
     * @param browser the name of the browser to use for the tests (optional, default is "Chrome").
     * @throws IllegalArgumentException if the provided browser name does not match "Chrome", "Firefox", or "Edge".
     */
    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browser) {
        switch (browser) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().browserVersion("131.0.6778.86").setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                Utils u = new Utils();
                driver.get(u.readProperty("MainUrl"));
                driver.manage().deleteAllCookies();
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                Utils u = new Utils();
                driver.get(u.readProperty("MainUrl"));
                driver.manage().deleteAllCookies();
            }
            case "Edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                Utils u = new Utils();
                driver.get(u.readProperty("MainUrl"));
                driver.manage().deleteAllCookies();
            }
            default -> throw new IllegalArgumentException("no such browser " + browser);
        }
    }

    /**
     * This method is executed after each test method, as part of the `@AfterMethod` annotation.
     * --
     * If the test has failed, it captures a screenshot and saves it in the "ScreenShots" folder
     * in the current working directory. The screenshot is saved with the name of the test that failed.
     * --
     * The method performs the following actions:
     * 1. Checks if the test result is a failure.
     * 2. If the test failed, it captures a screenshot using the `TakesScreenshot` interface.
     * 3. Saves the screenshot in the "./ScreenShots/" directory with the name of the test method that failed.
     *
     * @param result the result of the test execution, which includes the status of the test (PASS/FAIL).
     */
    @AfterMethod
    public void failedTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./ScreenShots/" + result.getName() + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is executed after all test methods in the class have finished executing,
     * as part of the `@AfterClass` annotation.
     *--
     * It performs the following actions:
     * 1. Waits for a brief moment to allow any remaining operations to complete.
     * 2. Quits the WebDriver, which closes all browser windows and ends the WebDriver session.
     *--
     * Additionally, there is an optional part of the code (commented out) to forcefully kill
     * the `chromedriver` process if running a single test, but this part should be used only
     * when running tests individually, not as part of a test suite.
     */
    @AfterClass
    public void tearDown() {
        new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.quit();
        //You may activate the following when running a single test:
//        try {
//            Runtime.getRuntime().exec("taskkill.exe /F /IM chromedriver.exe /T");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
