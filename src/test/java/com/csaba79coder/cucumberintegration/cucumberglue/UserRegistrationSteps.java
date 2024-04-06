package com.csaba79coder.cucumberintegration.cucumberglue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RequiredArgsConstructor
public class UserRegistrationSteps {

    private final CucumberStep cucumberStep;

    @Given("^the user is on the registration page$")
    public void theUserIsOnTheRegistrationPage() {
        // No action required for this step
    }

    @When("^the user enters valid username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theUserEntersValidUsernameAndPassword(String username, String password) {
        cucumberStep.sendRegisterRequest(username, password);
    }

    @Then("^the user should be registered successfully$")
    public void theUserShouldBeRegisteredSuccessfully() {
        // Implement code to verify successful registration
        // For example, you can assert the response status code
        assertEquals(HttpStatus.CREATED, cucumberStep.getLastResponse().getStatusCode());
    }

    @When("^the user enters existing username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theUserEntersExistingUsernameAndPassword(String username, String password) {
        cucumberStep.sendRegisterRequest(username, password);
    }

    @Then("^the user should see an error message indicating that the username already exists$")
    public void theUserShouldSeeAnErrorMessageIndicatingUsernameAlreadyExists() {
        // Retrieve the response body
        ResponseEntity<String> response = cucumberStep.getLastResponse();
        assertNotNull("Response body should not be null", response);

        String responseBody = response.getBody();
        assertNotNull("Response body should not be null", responseBody);
        // Implement code to verify the error message
        assertTrue("Response body should contain the details of the existing user",
                responseBody.contains("{\"id\":2,\"username\":\"existinguser\",\"password\":\"password123\"}"));
    }
}
