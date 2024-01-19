package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;

public class US205_TC02 extends BaseDriver {
    @Test
    public void loginWithInvalidCredentialsTest2() {    // with array

        driver.get("https://demowebshop.tricentis.com/");

        String[] emailArray = {"", "ozgur@gmail.com", "", "invalid91@gmail.com", "ozgur@gmail.com"};
        String[] passwordArray = {"", "", "Qwerty12", "invalidPwd12", "invalidPwd12"};

        try {
            actions.click(driver.findElement(By.cssSelector(".ico-logout"))).perform();
        } catch (Exception e) {
            System.out.println("This step was skipped because there was no login.");
        }

        for (int i = 0; i < emailArray.length; i++) {
            actions.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
            actions.sendKeys(driver.findElement(By.cssSelector("#Email")), emailArray[i]);
            actions.sendKeys(driver.findElement(By.cssSelector("#Password")), passwordArray[i]);
            actions.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
            actions.perform();
            String result = driver.findElement(By.cssSelector(".validation-summary-errors > span")).getText();
            Assert.assertEquals("Login was unsuccessful. Please correct the errors and try again.", result);
        }

        quitDriver();
    }
}
