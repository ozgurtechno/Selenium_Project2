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


}
