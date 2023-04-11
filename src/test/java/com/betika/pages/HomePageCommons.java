package com.betika.pages;

import com.betika.pojo.MatchesSelected;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.betika.utility.BrowserUtil.waitFor;
import static com.betika.utility.Driver.getDriver;

public class HomePageCommons {
    @FindBy(xpath = "//span[(.)='Profile']")
    private WebElement profileBtn;

    @FindBy(xpath = "//span[(.)='My Bets']")
    private WebElement myBetsBtn;

    @FindAll({
            @FindBy(xpath = "//div[@class='prebet-match']")
    })
    private List<WebElement> matches;  // displayed matches

    @FindAll({
            @FindBy(xpath = "//div[@class='stacked']//a")
    })
    private List<WebElement> bets; // Selected bets

    WebElement getSelectedOdd(int matchNum, int betSlct) {
        return getDriver().findElement(By.xpath("//div[@class='prebet-match'][" + matchNum +"]//div[@class='prebet-match__odds']/button[" + betSlct + "]"));
    }

    public HomePageCommons() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     *
     * @param btn
     * @return bool, whether element is displayed
     */
    public boolean buttonIsDisplayed(String btn) {
        switch (btn.toLowerCase()) {
            case "profile":
                return profileBtn.isDisplayed();
            case "my bets":
                return myBetsBtn.isDisplayed();
        }

        return false;
    }

    /**
     * Get the number of matches displayed
     * This will be used to generate random bets
     * @return the number of matches displayed
     */
    public int matchesDisplayed() {
        return matches.size();
    }

    public MatchesSelected getTeamsMatch(int matchNum, int betToPlace) {
        HashMap<String, String> match = new HashMap<>();

        waitFor(3);

        String teamA = getDriver().findElement(By.xpath("//div[@class='prebet-match'][" + matchNum +"]//div[@class='prebet-match__teams']/span[1]")).getText();
        String teamB = getDriver().findElement(By.xpath("//div[@class='prebet-match'][" + matchNum +"]//div[@class='prebet-match__teams']/span[2]")).getText();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement selectedOdd = getSelectedOdd(matchNum, betToPlace);

        wait.until(ExpectedConditions.elementToBeClickable(selectedOdd));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", selectedOdd);

        try {
            selectedOdd.click();
        } catch (ElementClickInterceptedException ex) {
            getDriver().navigate().refresh();
            waitFor(10);

            if (betToPlace == 3)
                betToPlace -= 1;
            else
                betToPlace += 1;

            selectedOdd = getSelectedOdd(matchNum, betToPlace);

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", selectedOdd);

            selectedOdd.click();
        }

        match.put("teamA", teamA);
        match.put("teamB", teamB);

        return new MatchesSelected(betToPlace, match);
    }

    public List<HashMap<String, String>> getBets() {
        List<HashMap<String, String>> betsOnSlip = new ArrayList<>();

        for (WebElement bet: bets) {
            HashMap<String, String> betOnSlip = new HashMap<>();

            String teamsOnBet = bet.getText();
            String[] teamArr = teamsOnBet.split(" Vs. ");

            betOnSlip.put("team1", teamArr[0]);
            betOnSlip.put("team2", teamArr[1]);

            betsOnSlip.add(betOnSlip);
        }

        return betsOnSlip;
    }
}
