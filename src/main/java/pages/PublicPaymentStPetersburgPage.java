package pages;

import core.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class PublicPaymentStPetersburgPage  extends Element {

    private By allStPeterburgPublicPayments = By.xpath("//div[@data-qa-type='payments/providersMenu']");

    public By getAllStPeterburgPublicPayments() {
        return allStPeterburgPublicPayments;
    }

    @Step("Verify that city name is Moscow")
    public ArrayList getAllPeterburgPublicPayments(By locator) {
        ArrayList elements = new ArrayList();

        for (int i = 0; i <selectFromList(locator).size() ; i++) {
            String elementsPublicPayments = selectFromList(locator).get(i).getText();
            elements.add(elementsPublicPayments);

        }
       return elements;
    }
}
