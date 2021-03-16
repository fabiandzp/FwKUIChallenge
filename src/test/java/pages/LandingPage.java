package pages;



import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.apache.logging.log4j.LogManager.getLogger;


public class LandingPage extends BasePage {
    private final By searchBar = By.className("site-search__controls__input");
    private final By signUp = By.cssSelector(".site-header__nav__link[href='/signup']");
    private static final Logger log = getLogger(LandingPage.class.getName());

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public SignUpPage signUpPage(){
        log.info("Clicking SignUp link page");
        driver.findElement(signUp).click();
        closeLocationBanner();
        return new SignUpPage(driver);
    }

    public void search(String query) {
        closeLocationBanner();
        log.info("Start method Search Item");
        driver.findElement(searchBar).sendKeys(query);
        driver.findElement(searchBar).sendKeys(Keys.RETURN);
    }

    private final By DoNotUpdateBanner = By.cssSelector("a[href='/users/international_settings/revert']");
    public void closeLocationBanner(){
        log.info("Waiting for the location Banner to close");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(DoNotUpdateBanner));
        if (driver.findElement(DoNotUpdateBanner).isDisplayed()){
            driver.findElement(DoNotUpdateBanner).click();
        }
    }



}
