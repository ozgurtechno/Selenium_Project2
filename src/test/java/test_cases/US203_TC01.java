package test_cases;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class US203_TC01 extends BaseDriver {

    @Test
    @Parameters({"email", "password"})
    public void logoutTest(@Optional("ozgur@gmail.com") String email, @Optional("Qwerty12") String password) {

        driver.get("https://demowebshop.tricentis.com/");
        login(email, password);
        // Logout
        actions.click(driver.findElement(By.cssSelector(".ico-logout"))).perform();
        String logIn = driver.findElement(By.cssSelector(".ico-login")).getText();
        Assert.assertEquals("Log in", logIn);
        quitDriver();
    }

    public void login(String email, String password){
        actions.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
        actions.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        actions.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        actions.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
        actions.perform();

    }
}
