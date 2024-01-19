package test_cases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import utility.BaseDriver;

public class US205_TC02 extends BaseDriver {
    @Test(dataProvider = "loginData")
    public void loginWithInvalidCredentialsTest2(String email, String password) {

        driver.get("https://demowebshop.tricentis.com/");
        actions.click(driver.findElement(By.cssSelector(".ico-login"))).perform();
        actions.sendKeys(driver.findElement(By.cssSelector("#Email")), email);
        actions.sendKeys(driver.findElement(By.cssSelector("#Password")), password);
        actions.click(driver.findElement(By.xpath("//input[@type='submit'][@value='Log in']")));
        actions.perform();
        String result = driver.findElement(By.cssSelector(".validation-summary-errors > span")).getText();
        Assert.assertEquals("Login was unsuccessful. Please correct the errors and try again.", result);

    }

    @DataProvider(name = "loginData")
    public Object[][] credentials() {
        Object[][] credentialsArray = {
                {"", ""},
                {"valid@gmail.com", ""},
                {"", "Qwerty12"},
                {"invalid91@gmail.com", "invalidPwd12"}
        };
        return credentialsArray;
    }
}
