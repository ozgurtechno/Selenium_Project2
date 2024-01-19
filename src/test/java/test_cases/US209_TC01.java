package test_cases;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utility.BaseDriver;
import org.junit.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

public class US209_TC01 extends BaseDriver {
    @Test
    public void invoiceDisplay() {

        driver.get("https://demowebshop.tricentis.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        if (!driver.findElement(By.cssSelector(".ico-login")).isDisplayed()) {
            System.out.println("You are already logged out");
            return;
        }

        action.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
        action.sendKeys(driver.findElement(By.cssSelector("#Email")), "fastest91@gmail.com");
        action.sendKeys(driver.findElement(By.cssSelector("#Password")), "Qwerty12");
        action.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
        action.perform();
        action.click(driver.findElement(By.cssSelector(".account"))).perform();
        action.click(driver.findElement(By.xpath("(//a[text()='Orders'])[1]"))).perform();
        action.click(driver.findElement(By.xpath("(//input[@type='button'])[1]"))).perform();
        action.click(driver.findElement(By.cssSelector("[class='button-2 pdf-order-button']"))).perform();

        WebElement orderText =
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".order-number")));
        String orderNumber = orderText.getText().substring(7);
        System.out.println("orderNumber = " + orderNumber);
        Assert.assertEquals(orderNumber, "1584519");
        delayQuit();
    }
}
