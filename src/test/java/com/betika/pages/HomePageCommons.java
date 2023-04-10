package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.betika.utility.Driver.getDriver;

public class HomePageCommons {
    @FindBy(xpath = "//span[(.)='Profile']")
    private WebElement profileBtn;

    @FindBy(xpath = "//span[(.)='My Bets']")
    private WebElement myBetsBtn;
    public HomePageCommons() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean buttonIsDisplayed(String btn) {
        switch (btn.toLowerCase()) {
            case "profile":
                return profileBtn.isDisplayed();
            case "my bets":
                return myBetsBtn.isDisplayed();
        }

        return false;
    }
}
