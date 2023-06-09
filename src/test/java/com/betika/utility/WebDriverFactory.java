package com.betika.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    /*
        This utility method will produce a webdriver method depending on the browser name passed e.g.
        WebDriverFactory("chrome") >> gives chrome driver
     */

    public static WebDriver getDriver(String browser_name) {
        WebDriver driver;

        switch (browser_name.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver = (WebDriver) new OperaDriverManager();
                break;
            default:
                driver = null;
                System.err.println("Unknown browser type " + browser_name);
        }

        return driver;
    }
}
