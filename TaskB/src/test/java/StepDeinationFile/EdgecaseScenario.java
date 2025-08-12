package StepDeinationFile;

import static org.testng.AssertJUnit.assertEquals;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EdgecaseScenario {
	private Response response;
	
	@Given("I will set the Genderize.io base URI to {string}")
	public void setBaseUri(String uri) {
        RestAssured.baseURI = uri;
    }

	@When("I send GET request without name parameter")
	 public void noNameParameter() {
        response = RestAssured.given().get().then().extract().response();
    }
	@Then("the response status code should be {int}")
	public void checkStatus(int expectedStatus) {
		assertEquals(expectedStatus, response.getStatusCode());
    }

	@And("the error message should be {string}")
	 public void checkErrorMessage(String expected) {
        String actual = response.jsonPath().getString("error");
        assertEquals(expected, actual);
    }

	@When("I send GET request with name parameter as empty")
	public void emptyNameParameter() {
        response = RestAssured.given()
        		.queryParam("name", "")
        		.get()
        		.then()
        		.extract()
        		.response();
        System.out.println("Final API Response: " + response.asString());
    }

}
