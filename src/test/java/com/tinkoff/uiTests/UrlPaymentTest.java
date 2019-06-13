package com.tinkoff.uiTests;

import com.tinkoff.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PaymentsPage;
import pages.PublicPaymentsMoscowPage;

import java.util.ResourceBundle;

@Epic("Url tests")
@Feature("Url tests")
public class UrlPaymentTest extends TestBase {

    @Story("Check Moscow payment url")
    @Test(description = "Check Moscow payment url")
    public void verifyUrlTest() {
        String urlExpected = "https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay";
        String urlActual;
        String url = ResourceBundle.getBundle("main").getString("BaseUrl");

        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();
        PaymentsPage paymentsPage = new PaymentsPage();

        windows.navigate(url);
        button.click(mainPage.getPayments());
        fields.type(paymentsPage.getSearchPaymentInput(), "ЖКУ-Москва");
        paymentsPage.getFirstElementSearchListPayments(paymentsPage.getFirstElementSearchPaymentsList());
        paymentsPage.clickFirstElementSearchListPayments(paymentsPage.getFirstElementSearchPaymentsList());
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.getPayPaymentsMoscow());
        paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");
        urlActual = windows.getCurrentUrl();

        Assert.assertEquals(urlActual, urlExpected, "Wrong value of parameter url.");
    }
}
