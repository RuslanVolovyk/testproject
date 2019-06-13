package pages;

import core.Element;
import elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PublicPaymentsMoscowPage {

    private By payPaymentsMoscow = By.xpath("//span[text()='Оплатить ЖКУ в Москве']");
    private By cityText = By.xpath("//span[@class='Link__link_cnGq8 Link__link_color_blue_JfY21 Link__link_type_simple_2yvi8 Link__link_nodecorated_2N9sy']");
    private By payerCodeInput = By.id("payerCode");
    private By periodInput = By.xpath("//input[@name='provider-period']");
    private By payButton = By.xpath("//h2[@data-qa-file='UIButton']");
    private By paymentAmountField = By.xpath("//div[@class='Input__value_3uAc-']//input[@class='Input__valueContent_3N2Qn Input__valueContent_primary_30SE8']");
    private By errorMessageTextPayerCode = By.xpath("//div[@class='ui-form__field']//descendant::div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']"); //Поле обязательное
    private By errorMessageTextPayPeriod = By.xpath("//div[@class='ui-inputdate']/following-sibling::div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']");
    private By errorMessageTextTotalPayment = By.xpath("(//div[@class='ui-form__field']//following-sibling::div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form'])[last()]");

    public By getPayPaymentsMoscow() {
        return payPaymentsMoscow;
    }

    public By getCityText() {
        return cityText;
    }

    public By getPayerCodeInput() {
        return payerCodeInput;
    }

    public By getPeriodInput() {
        return periodInput;
    }

    public By getPayButton() {
        return payButton;
    }

    public By getPaymentAmountField() {
        return paymentAmountField;
    }

    public By getErrorMessageTextPayerCode() {
        return errorMessageTextPayerCode;
    }

    public By getErrorMessageTextPayPeriod() {
        return errorMessageTextPayPeriod;
    }

    public By getErrorMessageTextTotalPayment() {
        return errorMessageTextTotalPayment;
    }

    private Button button = new Button();

    @Step("Choose payment region {0}")
    public void chooseRegion(By expectedRegion) {
        button.click(cityText);
        button.click(expectedRegion);
    }
}
