package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();


    @Given("user is on log in page")
    public void user_is_on_log_in_page() {
       Driver.getDriver().get(Config.getProperty("sauceDemo"));
    }
    @When("user inputs  valid username")
    public void user_inputs_valid_username() {
        loginPage.usernameInput.sendKeys(Config.getProperty("sauceDemoUsername"));
    }
    @When("user inputs valid password")
    public void user_inputs_valid_password() {
      loginPage.passwordInput.sendKeys(Config.getProperty("sauceDemoPassword"));
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }
    @Then("verify user logged in")
    public void verify_user_logged_in() {
        String expected = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expected,Driver.getDriver().getCurrentUrl());
    }











}
