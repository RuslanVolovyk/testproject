package com.tinkoff.uiTests;

import com.tinkoff.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.ResourceBundle;

@Epic("Functional tests ")
@Feature("Payment tests")

public class PaymentTest extends TestBase {

    @DataProvider(name = "payerCode")
    public Object[][] payerCode() {
        return new Object[][]{{"1"}, {"912222222"}, {" "}, {"dcsefww"}};
    }

    @Story("Negative, check error message for 'payer code' field")
    @Test(dataProvider = "payerCode", description = "Negative, check error message for 'payer code' field")
    public void verifyErrorMessagePayerCodeTest(String payerCode) {
        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();

        String url = ResourceBundle.getBundle("main").getString("BaseUrl");
        String errorMessage;
        windows.navigate(url);
        button.click(mainPage.getPayments());
        button.click(mainPage.getPublicPayments());
        mainPage.verifyCityName(mainPage.getCityText());
        button.click(mainPage.getPublicPaymentsMoscow());
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.getPayPaymentsMoscow());

        if (payerCode.equals("1") || payerCode.equals("912222222")) {
            fields.typeAndSubmit(publicPaymentsMoscowPage.getPayerCodeInput(), payerCode);
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextPayerCode());

            Assert.assertEquals(errorMessage, "Поле неправильно заполнено", "Wrong error text message to expected");
        } else {
            fields.type(publicPaymentsMoscowPage.getPayerCodeInput(), payerCode);
            button.click(publicPaymentsMoscowPage.getPayButton());
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextPayerCode());

            Assert.assertEquals(errorMessage, "Поле обязательное", "Wrong error text message to expected");
        }
    }

    @DataProvider(name = "payPeriod")
    public Object[][] payPeriod() {
        return new Object[][]{{"1"}, {"13.0000"}, {"11."}, {"wwtgfb"}, {" "}};
    }

    @Story("Negative, check error message for 'periodInput' field")
    @Test(dataProvider = "payPeriod", description = "Negative, check error message for 'periodInput' field")
    public void verifyErrorMessagePayPeriodTest(String payPeriod) {
        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();
        PaymentsPage paymentsPage = new PaymentsPage();

        String url = ResourceBundle.getBundle("main").getString("BaseUrl");
        String errorMessage;
        windows.navigate(url);
        button.click(mainPage.getPayments());
        button.click(mainPage.getPublicPayments());
        mainPage.verifyCityName(mainPage.getCityText());
        button.click(mainPage.getPublicPaymentsMoscow());
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.getPayPaymentsMoscow());

        if (payPeriod.equals("1") || payPeriod.equals("13.0000") || payPeriod.equals("11.")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");

            fields.typeAndSubmit(publicPaymentsMoscowPage.getPeriodInput(), payPeriod);
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextPayPeriod());

            Assert.assertEquals(errorMessage, "Поле заполнено некорректно", "Wrong error text message to expected");
        } else {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");

            fields.type(publicPaymentsMoscowPage.getPeriodInput(), payPeriod);
            button.click(publicPaymentsMoscowPage.getPayButton());
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextPayPeriod());

            Assert.assertEquals(errorMessage, "Поле обязательное", "Wrong error text message to expected");
        }
    }

    @DataProvider(name = "totalPayment")
    public Object[][] totalPayment() {
        return new Object[][]{{"-*-/////+"}, {"34532342342323232322323"}, {" "}, {"0,01"}, {"9,99"}, {"15000,01"}};
    }

    @Story("Negative, check error message for 'totalPayment' field")
    @Test(dataProvider = "totalPayment", description = "Negative, check error message for 'totalPayment' field")
    public void verifyErrorMessageTotalPaymentTest(String totalPayment) {
        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();
        PaymentsPage paymentsPage = new PaymentsPage();

        String url = ResourceBundle.getBundle("main").getString("BaseUrl");
        String errorMessage;
        windows.navigate(url);
        button.click(mainPage.getPayments());
        button.click(mainPage.getPublicPayments());
        mainPage.verifyCityName(mainPage.getCityText());
        button.click(mainPage.getPublicPaymentsMoscow());
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.getPayPaymentsMoscow());

        if (totalPayment.equals("-*-/////+") || totalPayment.equals("34532342342323232322323") || totalPayment.equals("11.")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.getPaymentAmountField(), totalPayment);
            button.click(publicPaymentsMoscowPage.getPayButton());
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextTotalPayment());

            Assert.assertEquals(errorMessage, "Поле заполнено неверно", "Wrong error text message to expected");
        } else if (totalPayment.equals("0,01") || totalPayment.equals("9,99")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.getPaymentAmountField(), totalPayment);
            button.click(publicPaymentsMoscowPage.getPayButton());
            paymentsPage.waitUntilTextPresentOnPage(publicPaymentsMoscowPage.getErrorMessageTextTotalPayment(), "Минимум — 10 \u20BD");
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextTotalPayment());


            Assert.assertEquals(errorMessage, "Минимум — 10 \u20BD", "Wrong error text message to expected");
        } else if (totalPayment.equals("15000,01")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.getPaymentAmountField(), totalPayment);
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextTotalPayment());

            Assert.assertEquals(errorMessage, "Максимум — 15 000 \u20BD", "Wrong error text message to expected");
        } else {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.getPayPublicServiceText(), "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.getPaymentAmountField(), totalPayment);
            button.click(publicPaymentsMoscowPage.getPayButton());
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.getErrorMessageTextTotalPayment());

            Assert.assertEquals(errorMessage, "Поле обязательное", "Wrong error text message to expected");
        }
    }

    @Story("Check that Moscow public payment first on the list")
    @Test(description = "Check that Moscow public payment first on the list")
    public void verifyOrderMoscowPublicPaymentTest() {
        String firstElementActual;
        String firstElementExpected = "ЖКУ-Москва\n" +
                "Коммунальные платежи";

        PaymentsPage paymentsPage = new PaymentsPage();
        String url = ResourceBundle.getBundle("main").getString("BaseUrl");

        windows.navigate(url);
        button.click(mainPage.getPayments());
        fields.type(paymentsPage.getSearchPaymentInput(), "ЖКУ-Москва");
        firstElementActual = paymentsPage.getFirstElementSearchListPayments(paymentsPage.getFirstElementSearchPaymentsList());

        Assert.assertEquals(firstElementActual, firstElementExpected, "Wrong public payment region.");
    }
}
