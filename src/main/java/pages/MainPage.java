package pages;

import core.Element;
import elements.Button;
import elements.ElementProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainPage  {

    private By payments = By.xpath("//a[text()='Платежи']");
    private By publicPayments = By.xpath("(//div[@class='Icon__icon_3c1E8']/*[@stroke='currentColor'])[2]");
    private By cityText = By.xpath("//span[@class='Link__link_cnGq8 Link__link_color_blue_JfY21 Link__link_type_simple_2yvi8 Link__link_nodecorated_2N9sy']");
    private By moscowCity = By.xpath("//span[text()='г. Москва']");
    private By publicPaymentsMoscow = By.xpath("//div[@aria-label='ЖКУ-Москва']");

    public By getPayments() {
        return payments;
    }

    public By getPublicPayments() {
        return publicPayments;
    }

    public By getCityText() {
        return cityText;
    }

    public By getMoscowCity() {
        return moscowCity;
    }

    public By getPublicPaymentsMoscow() {
        return publicPaymentsMoscow;
    }

    private ElementProperties elementProperties = new ElementProperties();
    private Button button = new Button();

    @Step("Verify that city name is Moscow")
    public void verifyCityName(By locator) {
        String cityName = elementProperties.getText(locator);
        if (!cityName.equals("Москве")){
            button.click(cityText);
            button.click(moscowCity);
            System.out.println("City name = " + cityName);
        }
    }
}
