package tests;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import static java.lang.System.setProperty;
import static org.apache.logging.log4j.LogManager.getLogger;

public class Hooks {

    protected WebDriver driver;
    private static Properties props;
    private static final Logger log = getLogger(LandingPage.class.getName());


    //Run before each test
    @BeforeMethod
    public void setup(){
        props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/application.properties"));
        } catch (IOException var2) {
            System.out.println("Error when reading the property file");
        }

        //System.setProperty("webdriver.chrome.driver","src/test/resources/browserBinaries/chromedriver.exe");
        //setProperty("webdriver.chrome.driver", "C:\\Projects\\FwKUIChallenge\\src\\test\\resources\\browserDrivers\\chromedriver.exe");
        setProperty("webdriver.chrome.driver", "src/test/resources/browserDrivers/chromedriver.exe");


        String url = props.getProperty("url");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
    }

}
