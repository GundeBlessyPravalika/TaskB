package RunnerFile;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

    features = {"src/test/resources/featuresfilea/genz.feature"},
    glue = {"StepDeinationFile"},
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber-reports.html"}


)
public class Runner {
	
}


