package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


	@RunWith(Cucumber.class)
	@CucumberOptions(features="src/test/java/features/library.feature",plugin ="html:target/jsonReports/cucumber-report.html",glue= {"stepDefinations"})
	public class TestRunner1 {
	//tags= {"@DeletePlace"}  compile test verify
	}


