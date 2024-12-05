package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
//@CucumberOptions(features="src/test/java/features",plugin ="html:target/jsonReports/cucumber-report.html",glue= {"stepDefinations"})
//public class TestRunner {
////tags= {"@DeletePlace"}  compile test verify
//}
@CucumberOptions(
plugin = {"pretty",
        "html:target/report/cucumber.html",
        "json:target/report/cucumber.json"
        },
features = {"C:\\Users\\Prajwal M A\\eclipse-workspace\\sampleproj\\src\\test\\java\\features"},
glue = {"stepDefinations"}// give the directory of stepDefinition

        )
public class TestRunner {
	//tags= {"@DeletePlace"}  compile test verify
	}