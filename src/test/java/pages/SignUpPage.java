package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;

public class SignUpPage extends BasePage {
    private static final Logger log = getLogger(SignUpPage.class.getName());

    public SignUpPage(WebDriver driver){
        super(driver);
    }

    private By firstName = By.id("user_first_name");
    private By lastName = By.id("user_last_name");
    private By email = By.id("user_email");
    private By password = By.id("user_password");
    private By submit = By.cssSelector("input[name='commit']");
    private By activationBanner = By.cssSelector("a[data-success-message]");
    public void signUpForm(){
        log.info("Fill the SignUp Form");
        driver.findElement(firstName).sendKeys("Kilian");
        driver.findElement(lastName).sendKeys("Jornet");
        driver.findElement(email).sendKeys("kilianjornet87@gmail.com");
        driver.findElement(password).sendKeys("Everest123.!");

        log.info("Submit SignUp Form");
        driver.findElement(submit).click();

        log.info("Waiting for success activation Banner Header");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(activationBanner));
    }

    public By getBannerText(){
        log.info("Return By Banner locator");
        return activationBanner;
    }





}
