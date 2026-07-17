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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppiumMobileWebTest {

    private static final int HOLD_BROWSER_SECONDS = Integer.getInteger("appium.holdSeconds", 3);

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
        openApp();
        checkDate("29", "2", "2024");

        WebElement modalMessage = assertModalContains("is correct date time!");
        assertTrue(modalMessage.getText().contains("is correct date time!"));
    }

    @Test
    public void testInvalidDayFormatOnMobileBrowser() {
        openApp();
        checkDate("abc", "12", "2024");

        WebElement modalMessage = assertModalContains("Input data for Day is incorrect format!");
        assertEquals("Input data for Day is incorrect format!", modalMessage.getText());
    }

    @Test
    public void testDayInMonthModeOnMobileBrowser() {
        openApp();
        clickWithJavaScript(driver.findElement(By.id("switchModeBtn")));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("appTitle"), "Day In Month Checker"));
        assertFalse(driver.findElement(By.id("day")).isDisplayed());

        driver.findElement(By.id("month")).sendKeys("2");
        driver.findElement(By.id("year")).sendKeys("2024");
        clickWithJavaScript(driver.findElement(By.id("checkBtn")));

        WebElement modalMessage = assertModalContains("Month 2/2024 has 29 days.");
        assertEquals("Month 2/2024 has 29 days.", modalMessage.getText());
    }

    private void openApp() {
        // Use 10.0.2.2 to access localhost of the host machine from Android Emulator
        driver.get("http://10.0.2.2:8080/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("day")));
    }

    private void checkDate(String day, String month, String year) {
        driver.findElement(By.id("day")).sendKeys(day);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
        clickWithJavaScript(driver.findElement(By.id("checkBtn")));
    }

    private WebElement assertModalContains(String expectedText) {
        WebElement modalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalMessage")));
        wait.until(ExpectedConditions.textToBePresentInElement(modalMessage, expectedText));
        return modalMessage;
    }

    private void clickWithJavaScript(WebElement element) {
        // Use JavaScript click to avoid virtual keyboard overlapping the button
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            holdBrowserBeforeQuit();
            driver.quit();
        }
    }

    private void holdBrowserBeforeQuit() {
        if (HOLD_BROWSER_SECONDS <= 0) {
            return;
        }

        try {
            Thread.sleep(Duration.ofSeconds(HOLD_BROWSER_SECONDS).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
