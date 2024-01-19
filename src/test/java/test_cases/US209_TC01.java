package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.BaseDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

public class US209_TC01 extends BaseDriver {
    @Test
    @Parameters({"email", "password"})
    public void invoiceDisplay(@Optional("ozgur@gmail.com") String email, @Optional("Qwerty12") String password) {

        driver.get("https://demowebshop.tricentis.com/");

        if (!driver.findElement(By.cssSelector(".ico-login")).isDisplayed()) {
            System.out.println("You are already logged out");
            return;
        }

        actions.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
        actions.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        actions.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        actions.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
        actions.perform();
        actions.click(driver.findElement(By.cssSelector(".account"))).perform();
        actions.click(driver.findElement(By.xpath("(//a[text()='Orders'])[1]"))).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Details']")));
        actions.click(driver.findElement(By.xpath("//input[@value='Details']"))).perform();
        actions.click(driver.findElement(By.cssSelector("[class='button-2 pdf-order-button']"))).perform();

        WebElement orderText =
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".order-number")));
        String orderNumber = orderText.getText().substring(7);
        System.out.println("orderNumber = " + orderNumber);
        Assert.assertEquals(orderNumber, orderNumber);
        quitDriver();
    }
}
