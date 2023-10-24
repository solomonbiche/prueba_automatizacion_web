package auto.framework.selenium.cucumber.steps.google_main_steps;

import auto.framework.selenium.annotations.LazyAutowired;
import auto.framework.selenium.annotations.TakeScreenshot;
import auto.framework.selenium.pages.google.GoogleMain;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Value;


public class GoogleMainSteps {

    @LazyAutowired
    private GoogleMain googleMainPage;

    @Given("I open google website")
    public void iOpenGoogleWebsite() {
        googleMainPage.goToURL().clickAcceptAll();
    }

    @When("Search in google the word {string}")
    public void searchInGoogle(String text) throws InterruptedException {
        googleMainPage.searchWithText(text);
    }

    @Then("Enter the wikipedia link about Automatizacion")
    public void clickWikipediaLink() {

        googleMainPage.clickWikiLink();
    }

    @When("Find the year of the first automation process")
    public void findYearFirstAutomationProcess() {

        googleMainPage.clickFindDateProcess();
    }
    @TakeScreenshot
    @And("Take a screenshot to the page")
    public void Screenshot() {

        }

    }

