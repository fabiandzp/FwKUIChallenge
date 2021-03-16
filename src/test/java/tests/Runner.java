package tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.*;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Runner extends Hooks {

    private static final Logger log = getLogger(Runner.class.getName());

    @Test
    public void register(){
        LandingPage landing = new LandingPage(driver);

        log.info("Calling SignUp");
        SignUpPage signUpPage = landing.signUpPage();

        log.info("Filling the SignUp Form");
        signUpPage.signUpForm();

        By bannerText = signUpPage.getBannerText();
        String texts = driver.findElement(bannerText).getAttribute("data-success-message");

        log.info("Banner Text " + texts);
        assertThat("Attribute contains 'We just sent you an email!'", texts,
                equalTo("We just sent you an email! Please check your inbox and spam folder in a few minutes."));
        driver.close();
    }


    @Test
    public void logIn(){
        LoginPopUp loginPopUp = new LoginPopUp(driver);

        log.info("Calling login fancy box");
        loginPopUp.logIn();

        log.info("Getting the Account Name Holder");
        assertThat("Attribute contains 'Kilian Jornet'", loginPopUp.getAccountName(),
                equalTo("Kilian Jornet"));
        driver.close();
    }

    @Test
    public void failedLogin(){
        LoginPopUp loginPopUp = new LoginPopUp(driver);

        log.info("Calling login fancy box");
        String invalidLoginMessage = loginPopUp.loginFailed();

        assertThat("Login validation fail", invalidLoginMessage,
                equalTo("The password or email you entered is incorrect."));

        driver.close();

    }

    @Test
    public void searchItem(){
        //Launchwen and Query
        LandingPage landing = new LandingPage(driver);
        log.info("Search Item");
        String query = "Fender Stratocaster";
        landing.search(query);

        //Getting the resultList
        ResultsPage resultsPage = new ResultsPage(driver);
        By itemsList = resultsPage.resultsList();

        //Iteration of the list to match at least one of the elements
        String itemFromList = resultsPage.searchItemInResultList(itemsList, query);
        log.info(itemFromList);
        assertThat("The Item retrieved doesn't contains '"+query+"'", itemFromList, containsString(query));

        driver.close();
    }

    @Test
    public void searchAndFilterPrice(){
        LandingPage landingPage = new LandingPage(driver);
        String query = "Gibson Les Paul";

        log.info("Calling search method");
        landingPage.search(query);

        ResultsPage resultsPage = new ResultsPage(driver);
        String minVal = "1200";
        String maxVal = "1200";
        By resultList = resultsPage.priceFilter(minVal, maxVal);

        //Iteration of the list to match at least one of the elements
        String itemFromList = resultsPage.searchItemInResultList(resultList, query);
        log.info(itemFromList);
        assertThat("The Item retrieved doesn't contains '"+query+"'", itemFromList, containsString(query));

        driver.close();
    }


    @Test
    public void searchAndOrderPriceHighToLow(){
        LandingPage landingPage = new LandingPage(driver);
        String query = "Condenser Microphone";

        log.info("Calling search method");
        landingPage.search(query);

        ResultsPage resultsPage = new ResultsPage(driver);
        By resultList = resultsPage.orderPriceHighToLow();

        //Iteration of the list to match at least one of the elements
        String itemFromList = resultsPage.searchItemInResultList(resultList, query);
        log.info(itemFromList);
        assertThat("The Item retrieved doesn't contains '"+query+"'", itemFromList, containsString(query));

        driver.close();
    }

    @Test
    public void choseCategoryAndGo(){
        Categories categories = new Categories(driver);

        log.info("Clicking Drums Category");
        categories.choseCategory();

        log.info("Getting the Header Title of Acoustic Drums Page");
        assertThat("Attribute contains 'Acoustic Drums'", categories.choseCategory(),
                equalTo("Acoustic Drums"));
        driver.close();
    }



}
