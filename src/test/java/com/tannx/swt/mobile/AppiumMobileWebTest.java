package com.tannx.swt.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppiumMobileWebTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        // Instruct Appium to automate Chrome browser on the Android Emulator
        options.withBrowserName("Chrome");

        // Connect to Appium Server (default port 4723)
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testCheckDateOnMobileBrowser() {
        // Use 10.0.2.2 to access localhost of the host machine from Android Emulator
        driver.get("http://10.0.2.2:8080/");

        // Wait for page to load
        WebElement dayInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("day")));
        WebElement monthInput = driver.findElement(By.id("month"));
        WebElement yearInput = driver.findElement(By.id("year"));
        WebElement checkBtn = driver.findElement(By.id("checkBtn"));

        dayInput.sendKeys("29");
        monthInput.sendKeys("2");
        yearInput.sendKeys("2024");
        
        // Use JavaScript click to avoid virtual keyboard overlapping the button
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBtn);

        // Wait for modal message to appear and verify text
        WebElement modalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalMessage")));
        // Need to wait until text content updates
        wait.until(ExpectedConditions.textToBePresentInElement(modalMessage, "is correct date time!"));
        
        assertTrue(modalMessage.getText().contains("is correct date time!"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
