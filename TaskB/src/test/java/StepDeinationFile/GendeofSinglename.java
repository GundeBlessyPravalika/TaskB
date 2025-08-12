package StepDeinationFile;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class GendeofSinglename {
	private String name;
    private Response response;

    @Given("the name {string}")
    public void givenTheName(String name) {
        this.name = name;
    }

    @When("I call the Genderize API")
    public void whenICallTheGenderizeAPI() {
        response = given()
            .baseUri("https://api.genderize.io")
            .param("name", name)
            .when()
            .get();           
    }

    @Then("the response should indicate the gender is {string}")
    public void thenTheResponseShouldIndicateTheGenderIs(String expectedGender) {
        assertThat(response.jsonPath().getString("gender"), equalTo(expectedGender));
    }

    @And("the probability should be greater than {double}")
    public void thenTheProbabilityShouldBeGreaterThan(Double expectedProbability) {
        Double probability = response.jsonPath().getDouble("probability");
        assertThat(probability, greaterThan(expectedProbability));
    }
    
    @Then("print the API response")
    public void printTheAPIResponse() {
        System.out.println("Final API Response: " + response.asString());
    }
    
    
}
