package elements;

import core.Element;
import core.InitialDriver;
import org.openqa.selenium.By;

public class Fields extends Element {


    public void type(By locator, String text) {
        waitUntilVisible(locator);
        InitialDriver.getInstance().getDriver().findElement(locator).clear();
        InitialDriver.getInstance().getDriver().findElement(locator).sendKeys(text);
    }

    public void typeAndSubmit(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
        driver.findElement(locator).submit();
    }
}
