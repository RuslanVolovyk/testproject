package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class Element extends Base {

    protected WebDriver driver = InitialDriver.getInstance().getDriver();

    /**
     * Status of element
     * Get status of element
     *
     * @param webElementExpectedCondition WebElement element,By locator
     * @return getWebElement(ExpectedConditions.elementToBeClickable ( element));
     */
    public WebElement getWebElement(ExpectedCondition<WebElement> webElementExpectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        wait.pollingEvery(Duration.ofMillis(DELAY));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(InterruptedException.class);
        wait.ignoring(UnknownError.class);
        return wait.until(webElementExpectedCondition);
    }

    protected WebElement waitUntilClickable(By locator) {
        return getWebElement(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitUntilVisible(By locator) {
        return getWebElement(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Status of element
     * Get status of element
     *
     * @param stateElementExpectedCondition WebElement element,By locator
     * @return getWebStateOfElement(ExpectedConditions.elementToBeSelected ( element));
     */

    private boolean getWebStateOfElement(ExpectedCondition<Boolean> stateElementExpectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        wait.pollingEvery(Duration.ofMillis(DELAY));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(InterruptedException.class);
        return wait.until(stateElementExpectedCondition);
    }

    protected void waitUntilTextPresent(By locator, String text) {
        getWebStateOfElement(ExpectedConditions.textToBe(locator, text));
    }

    protected List<WebElement> selectFromList(By locator) {
        waitUntilClickable(locator);
        return driver.findElements(locator);
    }

    protected WebElement getWebElement(By locator) {
        waitUntilVisible(locator);
        return driver.findElement(locator);
    }
}
