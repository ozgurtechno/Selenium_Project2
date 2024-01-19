package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;


public class US202_TC01 extends BaseDriver {
    @Test
    @Parameters({"email", "password"})
    public void registerExistingUserTest(@Optional("ozgur@gmail.com") String email, @Optional("Qwerty12") String password) {

        driver.get("https://demowebshop.tricentis.com/");

        actions.click(driver.findElement(By.cssSelector(".ico-register"))).perform();
        actions.click(driver.findElement(By.cssSelector("#gender-male")));
        actions.sendKeys(driver.findElement(By.cssSelector("#FirstName")), "Faster");
        actions.sendKeys(driver.findElement(By.cssSelector("#LastName")), "Tester");
        actions.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        actions.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        actions.sendKeys(driver.findElement(By.cssSelector("#ConfirmPassword")), password);
        actions.click(driver.findElement(By.cssSelector("#register-button")));
        actions.perform();

        String result = driver.findElement(By.cssSelector(".validation-summary-errors > ul > li")).getText();
        Assert.assertEquals("The specified email already exists", result);

        quitDriver();
    }
}
