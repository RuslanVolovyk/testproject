package elements;

import core.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementProperties extends Element {

    @Override
    public WebElement getWebElement(ExpectedCondition<WebElement> webElementExpectedCondition) {
        TIME_OUT = 5;
        return super.getWebElement(webElementExpectedCondition);
    }

    public String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }
    public String getText1(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isEnabled(By locator) {
        return getWebElement(ExpectedConditions.elementToBeClickable(locator)) != null;
    }
}
