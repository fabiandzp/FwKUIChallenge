package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.apache.logging.log4j.LogManager.getLogger;

public class LoginPopUp extends BasePage {

    private static Properties props;

    public LoginPopUp (WebDriver driver){
        super(driver);
    }

    private static final Logger log = getLogger(LandingPage.class.getName());

    private final By signIn = By.cssSelector("li a[href='/signin']");
    private final By popup = By.id("new_user_session");
    private final By usernameLogin = By.id("user_session_login");
    private final By passwordLogin = By.id("user_session_password");
    private final By btnLogin = By.cssSelector("input[value='Log In']");

    public void logIn(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.closeLocationBanner();

        log.info("Clicking SignIn link page");
        driver.findElement(signIn).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(popup));

        props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var2) {
            System.out.println("Error when reading the property file");
        }

        log.info("Login Form");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        driver.findElement(usernameLogin).sendKeys(username);
        driver.findElement(passwordLogin).sendKeys(password);
        driver.findElement(btnLogin).click();

        driver.switchTo().window(winHandleBefore);

    }


    private final By menu = By.className("site-header__nav__link__icon");
    private final By subMenu = By.cssSelector("div[data-track-clicks='SiteMenu']");
    private final By accountName = By.cssSelector("div[class='bdb-1 bd--offwhite padding-1 pt-0 weight-bold']");
    public String getAccountName(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.closeLocationBanner();
        log.info("Mouse Over Action Step");
        Actions action = new Actions(driver);
        WebElement we = driver.findElements(menu).get(4);
        action.moveToElement(we).perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(subMenu));

        String accountNameHolder = driver.findElement(accountName).getText();
        log.info("Check Name " + accountNameHolder);
        return accountNameHolder;
    }

    private final By filedLoginMessage = By.className("alert-box--red");
    public String loginFailed(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.closeLocationBanner();

        log.info("Clicking SignIn link page");
        driver.findElement(signIn).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(popup));

        props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var2) {
            System.out.println("Error when reading the property file");
        }
        log.info("Login Form");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        driver.findElement(usernameLogin).sendKeys(username);
        driver.findElement(passwordLogin).sendKeys(password+"1");
        driver.findElement(btnLogin).click();
        String invalidLoginMessage = driver.findElement(filedLoginMessage).getText();

        driver.switchTo().window(winHandleBefore);
        return invalidLoginMessage;
    }

}
