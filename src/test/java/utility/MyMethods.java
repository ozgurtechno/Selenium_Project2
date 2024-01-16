package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyMethods extends BaseDriver {
    public static void myWait(int time) {

        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void login() {

        driver.get("https://www.lidl.com/");

        cookies();

        myWait(2);

        WebElement signIn = driver.findElement(By.cssSelector("a[href=\"/login\"]"));
        signIn.click();

        WebElement loginEmail = driver.findElement(By.cssSelector("input[type=\"email\"]"));
        loginEmail.sendKeys("ndougherty91@webmd.com");

        WebElement loginPassword = driver.findElement(By.cssSelector("input[type=\"password\"]"));
        loginPassword.sendKeys("Qwerty12");

        WebElement signInButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        signInButton.click();
    }

    public static void logout() {

        WebElement myAccountName = driver.findElement(By.cssSelector("span[class='user-name mobileAccountName']"));
        myAccountName.click();
        MyMethods.myWait(1);

        WebElement signOut = driver.findElement(By.cssSelector("li[data-test='signOut']"));
        signOut.click();
        MyMethods.myWait(2);
    }

    public static void cookies() {
        WebElement acceptCookiesButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        if (!(acceptCookiesButton == null))
            acceptCookiesButton.click();
    }
}
