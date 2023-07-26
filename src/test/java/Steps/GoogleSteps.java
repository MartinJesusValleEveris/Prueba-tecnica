package Steps;

import actions.Google;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GoogleSteps {
    private final Google google;

    public GoogleSteps(){
        google = new Google();
    }

    @Given("The next URL: {string}")
    public void loadUrl(String site) {
        google.openUrl(site);
    }

    @When("The user find {string} in the search")
    public void findWordGoogle(String word){
        google.selectInputSearch(word);
    }

    @And("select the wikipedia Link")
    public void selectWikipediaLink(){
        google.selectWikipediaLink();
    }
}
