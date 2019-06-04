package elements;

import core.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class Button extends Element {

    public void click(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locator));
        try {
            waitUntilClickable(locator).click();
        } catch (StaleElementReferenceException e) {
            waitUntilClickable(locator).click();
        }
    }

    public void verifyPresenceOfElementLocatedThenClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    }
}
