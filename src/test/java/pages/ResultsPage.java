package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ResultsPage extends BasePage {
    private final By sideBarFilters = By.className("facet-sidebar__contents");
    private final By minValuePrice = By.cssSelector("input[placeholder='Min']");
    private final By maxValuePrice = By.cssSelector("input[placeholder='Max']");
    private final By searchFilter = By.cssSelector("button[class='search-input-group__button']");
    //private final By resultList = By.xpath("//li[contains(@class,'sortable-tile sortable-tile--4up-order')]");
    private final By resultList = By.xpath("//ul[@class='sortable-tiles']/li/div");

    private static final Logger log = getLogger(LandingPage.class.getName());

    public ResultsPage(WebDriver driver){
        super(driver);
    }

    public By priceFilter(String minValue, String maxValue){
        WebDriverWait wait =new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(sideBarFilters));

        log.info("Setting Min price value");
        driver.findElements(minValuePrice).get(0).sendKeys(minValue);

        log.info("Setting Max price value");
        driver.findElements(maxValuePrice).get(0).sendKeys(maxValue);

        wait.until(ExpectedConditions.elementToBeClickable(searchFilter));
        driver.findElements(searchFilter).get(1).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(resultList));

        return resultList;
    }


    private final By dropDown = By.id("sort");
    private final By getResultListHighToLow = By.className("grid-card__title");
    public By orderPriceHighToLow() {
        WebDriverWait wait =new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(dropDown));
        Select orderCriteria = new Select(driver.findElement(dropDown));
        orderCriteria.selectByValue("price|desc");

        wait.until(ExpectedConditions.presenceOfElementLocated(getResultListHighToLow));
        //int x = driver.findElements(getResultListHighToLow).size();

        return getResultListHighToLow;
    }

    public By resultsList (){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(resultList));
        return resultList;
    }


    public String searchItemInResultList(By itemsList, String query) {
        log.info("Search Item into the list");
        int numberOfItems = driver.findElements(itemsList).size();
        log.info("Elements present in the list: " + numberOfItems);
        int counter=0;
        for (int i=0; i < numberOfItems; i++) {
            String strValidation = driver.findElements(itemsList).get(i).getText();
            if (strValidation.contains(query)){
                counter = i;
                break;
            }

        }
        log.info("Element fund in Item: " + counter);
        String validationItem  = driver.findElements(itemsList).get(counter).getText();
        log.info("Item text: " + validationItem);
        return validationItem;
    }



}
