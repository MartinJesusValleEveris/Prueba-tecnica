package Steps;

import actions.Wikipedia;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class WikipediaSteps {

    private final Wikipedia wikipedia;

    public WikipediaSteps(){
        wikipedia = new Wikipedia();
    }

    @Then("The system displays the first automation in the {string}")
    public void checkDateAutomation(String date){
        Assert.assertTrue("The first automation year doesn't match with the value",wikipedia.checkDate(date));
    }

    @And("take a screenshot")
    public void takeScreenshot(){
        wikipedia.takeSreenshoot();

    }
}
