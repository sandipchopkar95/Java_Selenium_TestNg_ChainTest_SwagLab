package com.saucedemo.basetest;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.saucedemo.factory.SeleniumFactory;
import com.saucedemo.testutility.JiraTestCaseUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static com.saucedemo.testutility.JiraTestCaseUtils.attachJiraTestId;

@Listeners(ChainTestListener.class)
public class BaseTest {
    protected SeleniumFactory seleniumFactory;
    protected Properties prop;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, @Optional("false") String headless) {
        seleniumFactory = new SeleniumFactory();
        prop = seleniumFactory.init_prop();
        boolean headlessMode = seleniumFactory.isHeadless(headless);
        seleniumFactory.initDriver(browser, headlessMode);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().get(prop.getProperty("baseUrl", "https://www.saucedemo.com/v1/"));
    }

    protected WebDriver getDriver() {
        return SeleniumFactory.getDriver();
    }

    @AfterMethod
    public void attachScreenShot_And_TestCase(final ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        try {
            // Capture screenshot
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = Files.readAllBytes(screenshot.toPath());
            ChainTestListener.embed(screenshotBytes, "image/png");

            // Get the list of Jira Test IDs
            List<String> jiraTestIds = JiraTestCaseUtils.attachJiraTestId(method);

            // If no test IDs are available, log a warning
            if (jiraTestIds.isEmpty()) {
                ChainTestListener.log("Test Case IDs are not available for this test");
            } else {
                // Create and log a button for each Jira Test ID
                for (String jiraTestId : jiraTestIds) {
                    String hyperlinkButton = "<a href=\"" + jiraTestId +
                            "\" target=\"_blank\" style=\"display:inline-block; padding:10px 15px; background-color:#007BFF; color:white; text-decoration:none; border-radius:5px; margin:5px;\">Test Case : " + jiraTestId + "</a>";
                    ChainTestListener.log(hyperlinkButton);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @AfterMethod(dependsOnMethods = "attachScreenShot_And_TestCase")
    public void tearDown() {
        seleniumFactory.quitDriver();
    }
}
