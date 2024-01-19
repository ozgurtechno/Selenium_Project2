package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.BaseDriver;

import java.time.Duration;

public class US207_TC01 extends BaseDriver {
    @Test
    @Parameters({"email", "password"})
    public void voteTest(@Optional("ozgur@gmail.com") String email, @Optional("Qwerty12") String password) {

        driver.get("https://demowebshop.tricentis.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Actions action = new Actions(driver);
        action.click(driver.findElement(By.id("pollanswers-1"))).perform();
        action.click(driver.findElement(By.id("vote-poll-1"))).perform();
        WebElement warningMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Only registered users can vote.']")));
        Assert.assertTrue(warningMessage.getText().contains("Only registered users can vote"));

        action.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
        action.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        action.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        action.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
        action.perform();

        try {
            action.click(driver.findElement(By.id("pollanswers-1"))).perform();
            action.click(driver.findElement(By.id("vote-poll-1"))).perform();
        } catch (Exception e) {
            System.out.println("Survey vote has been previously submitted.");
        }

        WebElement voteText = driver.findElement(By.cssSelector(".poll-total-votes"));
        Assert.assertTrue(voteText.getText().contains("vote"));

        quitDriver();
    }
}
