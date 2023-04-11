package com.betika.steps;

import com.betika.pages.HomePageCommons;
import com.betika.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.betika.utility.BrowserUtil.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.betika.utility.ConfigReader.confRead;
import static com.betika.utility.Driver.getDriver;
import static org.hamcrest.Matchers.*;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    HomePageCommons homeCommons = new HomePageCommons();
    String loginUrl = confRead("betika_url") + confRead("login_subdirectory");
    String registerUrl = confRead("betika_url") + "register";

    WebDriver driver = getDriver();
    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {

        driver.get(loginUrl);
    }

    @When("the user gives the wrong {string} or {string}")
    public void theUserGivesTheWrongOr(String phoneNumber, String password) {
        loginPage.login(phoneNumber, password);
    }

    @When("the user gives the correct {string} and {string}")
    public void theUserGivesTheAnd(String phoneNumber, String password) {
        loginPage.login(confRead(phoneNumber), confRead(password));
    }

    @Then("the user should not be logged in")
    public void theUserShouldNotBeLoggedIn() {
        waitFor(5);

        assertThat(driver.getCurrentUrl(), anyOf(is(loginUrl), is(registerUrl)));
    }

    @And("the {string} prompt should appear")
    public void thePromptShouldAppear(String promptTxt) {
        assertThat(driver.findElement(By.xpath("//p[@class='message']")).isDisplayed(), is(true));
        assertThat(driver.findElement(By.xpath("//div[@class='title']")).getText(), is(promptTxt));
    }

    @Then("the user should be logged in and the profile button should be visible")
    public void theUserShouldBeLoggedIn() {
        WebElement profileBtn = homeCommons.getProfileBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(profileBtn));

        assertThat(homeCommons.buttonIsDisplayed("profile"), is(true));
    }

    @Then("there should be a prompt for the missing entry")
    public void thereShouldBeAPromptForTheMissingEntry() {
        assertThat(driver.findElement(By.xpath("//p[@class='text-error']")).isDisplayed(), is(true));
    }
}
