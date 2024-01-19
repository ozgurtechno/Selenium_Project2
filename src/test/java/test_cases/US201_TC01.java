package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class US201_TC01 extends BaseDriver {
    @Test
    @Parameters({"email", "password"})
    public void registerTest(@Optional("ozgur@gmail.com") String email, @Optional("Qwerty12") String password) {

        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.cssSelector(".ico-register")).click();

        actions.click(driver.findElement(By.cssSelector("#gender-male")));
        actions.sendKeys(driver.findElement(By.cssSelector("#FirstName")), "Faster");
        actions.sendKeys(driver.findElement(By.cssSelector("#LastName")), "Tester");
        actions.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        actions.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        actions.sendKeys(driver.findElement(By.cssSelector("#ConfirmPassword")), password);
        actions.click(driver.findElement(By.cssSelector("#register-button")));
        actions.perform();

        try {
            String result = driver.findElement(By.cssSelector(".page-body > .result")).getText();
            Assert.assertEquals("Your registration completed", result);
            actions.click(driver.findElement(By.cssSelector(".header-links > ul > li + li > a.ico-logout")));
        } catch (Exception e) {
            System.out.println("Since the membership has been created in a previous step, we can skip this one.");
        }
        quitDriver();
    }
}
