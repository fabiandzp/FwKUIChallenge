package steps;

import io.cucumber.java.en.*;

public class Login {
    @Given("the user wants to log in to his account")
    public void theUserWantsToLogInToHisAccount() {
        System.out.println("test");
    }

    @When("the user submits his credentials")
    public void theUserSubmitsHisCredentials() {
        System.out.println("test");
    }

    @Then("the user should be able to log in")
    public void theUserShouldBeAbleToLogIn() {
        System.out.println("test");
    }

    @But("the user has incorrect credentials")
    public void theUserHasIncorrectCredentials() {
        System.out.println("test");

    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        System.out.println("test");
    }

    @And("the user should not be able to log in")
    public void theUserShouldNotBeAbleToLogIn() {
        System.out.println("test");
    }
}
