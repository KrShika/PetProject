package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberReport.html","json:target/testReport.json"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@Login",
        dryRun = false
)

public class RunnerClass {
}