package com.saucedemo.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SeleniumFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private Properties prop;

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public WebDriver initDriver(String browserName, boolean headless) {
        System.out.println("Initializing browser: " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (headless) chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                tlDriver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                tlDriver.set(new EdgeDriver(edgeOptions));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return getDriver();
    }

    public Properties init_prop() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
        return prop;
    }

    public void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }

    public boolean isHeadless(String headless) {
        return Boolean.parseBoolean(headless != null ? headless : "false");
    }
}
