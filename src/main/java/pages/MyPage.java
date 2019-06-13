package pages;

import core.Element;
import org.openqa.selenium.By;

public class MyPage extends Element {
    private By sigInButton = By.xpath("//div[@class='header__user-link ng-star-inserted']");
    private By emailInput = By.xpath("//input[@id='signInEmail']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By sigIn = By.xpath("//button[@class='button button_medium button_blue authorization-form__button button_spinner']");
    private By verificationCodeInput = By.xpath("//input[@class='form_input validate ng-untouched ng-pristine ng-valid']");
    private By verifyButton = By.xpath("//button[@class='button button_medium button_blue authorization-form__button']");

    public By getVerifyButton() {
        return verifyButton;
    }

    public By getVerificationCodeInput() {
        return verificationCodeInput;
    }

    public By getSigIn() {
        return sigIn;
    }

    public By getEmailInput() {
        return emailInput;
    }

    public By getPasswordInput() {
        return passwordInput;
    }

    public By getSigInButton() {
        return sigInButton;
    }
}
