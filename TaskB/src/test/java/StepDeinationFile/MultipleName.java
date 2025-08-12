package StepDeinationFile;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MultipleName {
	private List<String> namesList;
    private Response response;

    @Given("I have the following names:")
    public void i_have_the_following_names(io.cucumber.datatable.DataTable table) {
        namesList = table.asList();
    }

    @When("I send a batch request to the Genderize.io API")
    public void i_send_a_batch_request_to_the_genderize_io_api() {
        response = RestAssured
            .given()
                .baseUri("https://api.genderize.io")
                .queryParams("name[]", namesList)
            .when()
                .get()
            .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Then("the response should contain all provided names")
    public void the_response_should_contain_all_provided_names() {
    	List<String> returnedNames = response.jsonPath().getList("name"); // list of all names
    	assertThat(returnedNames, containsInAnyOrder(namesList.toArray()));
    }

    @And("each entry should include gender, probability, and count")
    public void each_entry_should_include_gender_probability_and_count() {
        List<Map<String, Object>> result = response.jsonPath().getList("$");
        result.forEach(entry -> {
        	assertThat(entry, hasKey("gender"));
        	assertThat(entry, hasKey("probability"));
        	assertThat(entry, hasKey("count"));
            Double prob = ((Number)entry.get("probability")).doubleValue();
            assertThat(prob, allOf(greaterThanOrEqualTo(0.0), lessThanOrEqualTo(1.0)));
            System.out.println("Final API Response: " + response.asString());
        });
        
    }
}
