package com.betika.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

import static com.betika.utility.Driver.closeBrowser;
import static com.betika.utility.Driver.getDriver;

public class Hooks {
    @Before("@web-ui") // From cucumber
    public void setupDriver() {
        // Implicit Wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Maximize window size
        getDriver().manage().window().maximize();
    }

    @Before("@skip-scenario")
    public void skipScenario(Scenario scenario) {
        System.out.println("Skip scenario: " + scenario.getName());
        Assume.assumeTrue(false);
    }

    @After("@web-ui")
    public void tearDown(Scenario scenario) {
        // Taking screenshot if scenario is failed
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        // Close browser
        closeBrowser();
    }
}
