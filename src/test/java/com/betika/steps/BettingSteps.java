package com.betika.steps;

import com.betika.pages.CommonFunctions;
import com.betika.pages.HomePageCommons;
import com.betika.pojo.MatchesSelected;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.betika.pages.CommonFunctions.stringChecker;
import static com.betika.utility.BrowserUtil.waitFor;
import static com.betika.utility.ConfigReader.confRead;
import static com.betika.utility.Driver.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class BettingSteps {
    HomePageCommons homeCommons = new HomePageCommons();

    Random rand = new Random();

    List<MatchesSelected> betsPlaced = new ArrayList<>();
    @When("the user access the {string}")
    public void theUserAccessThe(String soccerSubDir) {
        getDriver().get(confRead("betika_url") + confRead(soccerSubDir));
    }

    @Then("there should be matches displayed")
    public void thereShouldBeMatchesDisplayed() {
        waitFor(5);

        System.out.println("homeCommons.matchesDisplayed() = " + homeCommons.matchesDisplayed());
        assertThat(homeCommons.matchesDisplayed(), greaterThan(1));
    }

    @And("the user selects a number of bets")
    public void theUserSelectsANumberOfBets() {
        int maxMatches = homeCommons.matchesDisplayed(); // To be used for random match selection
        Random random = new Random();

        // Selecting 3 random bets
        for (Integer integer : CommonFunctions.randNums(6)) {
            int betToPlace = random.nextInt(3) + 1;
            betsPlaced.add(homeCommons.getTeamsMatch(integer, betToPlace)); // ToDO change to max available matches. Fix click exception
            waitFor(3);
        }

        waitFor(10);
    }

    @Then("the selected bets should appear on the bet slip area")
    public void theSelectedBetsShouldAppearOnTheBetSlipArea() {

        String teamA = "", teamB = "", team1 = "", team2="";

        List<HashMap<String, String>> bets = homeCommons.getBets();
        List<MatchesSelected> matches = betsPlaced;

        System.out.println("bets = " + bets);
        System.out.println("bets.get(0).get(team1) = " + bets.get(0).get("team1"));
        System.out.println("matches = " + matches);
        System.out.println("matches.get(0).getTeamsPlaying().get(\"teamA\") = " + matches.get(0).getTeamsPlaying().get("teamA"));

        for (int i = 0; i < bets.size(); i++) {
            teamA = matches.get(i).getTeamsPlaying().get("teamA");

            teamB = matches.get(i).getTeamsPlaying().get("teamB");

            team1 = bets.get(i).get("team1");
            team2 = bets.get(i).get("team2");

            assertThat(stringChecker(teamA).equals(stringChecker(team1)), is(true));
            assertThat(stringChecker(teamB).equals(stringChecker(team2)), is(true));
        }
    }
}
