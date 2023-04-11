package com.betika.steps;

import com.betika.pages.CommonFunctions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.betika.utility.Driver.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;

public class DependenciesTest {
    static WebDriver driver = getDriver();

    @BeforeAll
    public static void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }

    public static void main(String[] args) {
        System.out.println(CommonFunctions.randNums(6));
    }
}
