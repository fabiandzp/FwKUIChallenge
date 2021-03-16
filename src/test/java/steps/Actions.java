package steps;

import io.cucumber.java.en.Given;

public class Actions {
    @Given("the user wants to buy a guitar")
    public void theUserWantsToBuyAGuitar() {
        System.out.println("Test");
    }
}
