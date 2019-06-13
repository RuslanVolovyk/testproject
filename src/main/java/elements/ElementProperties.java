package elements;

import core.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementProperties extends Element {

    @Override
    public WebElement getWebElement(ExpectedCondition<WebElement> webElementExpectedCondition) {
        TIME_OUT = 5;
        return super.getWebElement(webElementExpectedCondition);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
