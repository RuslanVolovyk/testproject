package elements;

import core.Element;
import org.openqa.selenium.By;

public class Fields extends Element {


    public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
    public void typeAndSubmit(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
        driver.findElement(locator).submit();
    }
}
