package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Categories extends BasePage {
    private final By categoryDrums = By.cssSelector("button[data-header-category='drums']");

    private static final Logger log = getLogger(LandingPage.class.getName());

    public Categories(WebDriver driver){
        super(driver);
    }

    private final  By popupCategories = By.cssSelector("div[class='category-flyout-header category-flyout-header--open']");
    private final  By subSection = By.cssSelector("h3 a[href='https://reverb.com/c/drums-and-percussion/acoustic-drums']");
    private final  By headerTitle = By.cssSelector("header h1[class='cms-centered-page-head__title']");


    public String choseCategory(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        //Action
        log.info("Clicking Dumbs Category link page");
        driver.findElement(categoryDrums).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(popupCategories));

        log.info("Clicking Acoustic Drum-subCategory");
        driver.findElement(subSection).click();

        log.info("Getting the Header Title");
        String headerTitleText = driver.findElement(headerTitle).getText();

        return headerTitleText;

    }


}
