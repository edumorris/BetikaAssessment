package com.betika.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.betika.utility.Driver.getDriver;

@Getter
public class LoginPage {
    @FindBy(xpath = "//input[@type='text']")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='checkbox' and @class='checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//button/span[(.)='Login']")
    private WebElement loginBtn;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void login(String phone, String pwd) {
        phoneNumberInput.sendKeys(phone);
        passwordInput.sendKeys(pwd);
        loginBtn.click();
    }
}
