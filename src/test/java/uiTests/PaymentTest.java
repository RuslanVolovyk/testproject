package uiTests;

import listeners.Listener;
import elements.Button;
import elements.ElementProperties;
import elements.Fields;
import elements.Windows;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

@Listeners({Listener.class})
@Epic("Functional tests ")
@Feature("Payment tests")
public class PaymentTest {
    private Windows windows;
    private Button button;
    private Fields fields;
    private MainPage mainPage;
    private ElementProperties elementProperties;


    @BeforeMethod
    public void setUp() {
        windows = new Windows();
        button = new Button();
        fields = new Fields();
        mainPage = new MainPage();
        elementProperties = new ElementProperties();
    }

    @AfterClass
    public void tearDown() {
        windows = null;
        button = null;
        fields = null;
        mainPage = null;
        elementProperties = null;
    }

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
        button.click(mainPage.payments);
        button.click(mainPage.publicPayments);
        mainPage.verifyCityName(mainPage.cityText);
        button.click(mainPage.publicPaymentsMoscow);
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.payPaymentsMoscow);

        if (payerCode.equals("1") || payerCode.equals("912222222")) {
            fields.typeAndSubmit(publicPaymentsMoscowPage.payerCodeInput, payerCode);
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextPayerCode);

            Assert.assertEquals(errorMessage, "Поле неправильно заполнено", "Wrong error text message to expected");
        } else {
            fields.type(publicPaymentsMoscowPage.payerCodeInput, payerCode);
            button.click(publicPaymentsMoscowPage.payButton);
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextPayerCode);

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
        button.click(mainPage.payments);
        button.click(mainPage.publicPayments);
        mainPage.verifyCityName(mainPage.cityText);
        button.click(mainPage.publicPaymentsMoscow);
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.payPaymentsMoscow);

        if (payPeriod.equals("1") || payPeriod.equals("13.0000") || payPeriod.equals("11.")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");

            fields.typeAndSubmit(publicPaymentsMoscowPage.periodInput, payPeriod);
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.errorMessageTextPayPeriod);

            Assert.assertEquals(errorMessage, "Поле заполнено некорректно", "Wrong error text message to expected");
        } else {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");

            fields.type(publicPaymentsMoscowPage.periodInput, payPeriod);
            button.click(publicPaymentsMoscowPage.payButton);
            errorMessage = elementProperties.getText(publicPaymentsMoscowPage.errorMessageTextPayPeriod);

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
        button.click(mainPage.payments);
        button.click(mainPage.publicPayments);
        mainPage.verifyCityName(mainPage.cityText);
        button.click(mainPage.publicPaymentsMoscow);
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.payPaymentsMoscow);

        if (totalPayment.equals("-*-/////+") || totalPayment.equals("34532342342323232322323") || totalPayment.equals("11.")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.paymentAmountField, totalPayment);
            button.click(publicPaymentsMoscowPage.payButton);
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextTotalPayment);

            Assert.assertEquals(errorMessage, "Поле заполнено неверно", "Wrong error text message to expected");
        } else if (totalPayment.equals("0,01") || totalPayment.equals("9,99")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.paymentAmountField, totalPayment);
            button.click(publicPaymentsMoscowPage.payButton);
            paymentsPage.waitUntilTextPresentOnPage(publicPaymentsMoscowPage.errorMessageTextTotalPayment, "Минимум — 10 \u20BD");
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextTotalPayment);


            Assert.assertEquals(errorMessage, "Минимум — 10 \u20BD", "Wrong error text message to expected");
        } else if (totalPayment.equals("15000,01")) {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.paymentAmountField, totalPayment);
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextTotalPayment);

            Assert.assertEquals(errorMessage, "Максимум — 15 000 \u20BD", "Wrong error text message to expected");
        } else {
            paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");
            fields.type(publicPaymentsMoscowPage.paymentAmountField, totalPayment);
            button.click(publicPaymentsMoscowPage.payButton);
            errorMessage = elementProperties.getText1(publicPaymentsMoscowPage.errorMessageTextTotalPayment);

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
        button.click(mainPage.payments);
        fields.type(paymentsPage.searchPaymentInput, "ЖКУ-Москва");
        firstElementActual = paymentsPage.getFirstElementSearchListPayments(paymentsPage.firstElementSearchPaymentsList);

        Assert.assertEquals(firstElementActual, firstElementExpected, "Wrong public payment region.");
    }

    @Story("Check Moscow payment url")
    @Test(description = "Check Moscow payment url")
    public void verifyUrlTest() {
        String urlExpected = "https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay";
        String urlActual;
        String url = ResourceBundle.getBundle("main").getString("BaseUrl");

        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();
        PaymentsPage paymentsPage = new PaymentsPage();

        windows.navigate(url);
        button.click(mainPage.payments);
        fields.type(paymentsPage.searchPaymentInput, "ЖКУ-Москва");
        paymentsPage.getFirstElementSearchListPayments(paymentsPage.firstElementSearchPaymentsList);
        paymentsPage.clickFirstElementSearchListPayments(paymentsPage.firstElementSearchPaymentsList);
        button.verifyPresenceOfElementLocatedThenClick(publicPaymentsMoscowPage.payPaymentsMoscow);
        paymentsPage.waitUntilTextPresentOnPage(paymentsPage.payPublicServiceText, "Оплатите ЖКУ в Москве без комиссии");
        urlActual = windows.getCurrentUrl();

        Assert.assertEquals(urlActual, urlExpected, "Wrong value of parameter url.");
    }

    @Story("Negative, input St. Petersburg region")
    @Test(description = "Negative, input St. Petersburg region")
    public void changeDefaultRegionTest() {
        PublicPaymentsMoscowPage publicPaymentsMoscowPage = new PublicPaymentsMoscowPage();
        PublicPaymentStPetersburgPage publicPaymentStPetersburgPage = new PublicPaymentStPetersburgPage();
        RegionPage regionPage = new RegionPage();

        String PublicPaymentsMoscow = "ЖКУ-Москва";
        ArrayList actualPublicPaymentsList;
        String url = ResourceBundle.getBundle("main").getString("BaseUrl");

        windows.navigate(url);
        button.click(mainPage.payments);
        button.click(mainPage.publicPayments);
        publicPaymentsMoscowPage.chooseRegion(regionPage.StPetersburgRegion);

        actualPublicPaymentsList = publicPaymentStPetersburgPage
                .getAllPeterburgPublicPayments(publicPaymentStPetersburgPage.allStPeterburgPublicPayments);

        Assert.assertFalse(actualPublicPaymentsList.contains(PublicPaymentsMoscow), "Public payment Moscow exist on the public payment St. Petersburg page");
    }
}
