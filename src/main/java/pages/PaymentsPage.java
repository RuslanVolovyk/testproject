package pages;

import core.Element;
import elements.ElementProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class PaymentsPage extends Element {

    public By searchPaymentInput = By.xpath("//input[@class='Input__valueContent_3N2Qn Input__valueContent_alone__8cF1 Input__valueContent_primary_30SE8']");
    public By payPublicServiceText = By.xpath("//div[text()='Оплатите ЖКУ в Москве без комиссии']");
    public By firstElementSearchPaymentsList = By.xpath("//div[@class='Grid__root_3dqwx Grid__root_display_block_3j1gE']/child::div[1]");

    private ElementProperties elementProperties = new ElementProperties();

    @Step("Get text from the payment element")
    public String getFirstElementSearchListPayments(By locator) {
        return elementProperties.getText(locator);
    }

    @Step("Click on the first search payment element")
    public void clickFirstElementSearchListPayments(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locator)).build().perform();
        getWebElement(locator).findElements(By.cssSelector("div")).get(1).click();
    }

    @Step("Wait until the text {1} will be present on page")
    public void waitUntilTextPresentOnPage(By locator, String text) {
        waitUntilTextPresent(locator, text);
    }
}
