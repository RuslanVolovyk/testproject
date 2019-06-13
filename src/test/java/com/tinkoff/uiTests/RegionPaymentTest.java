package com.tinkoff.uiTests;

import com.tinkoff.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PublicPaymentStPetersburgPage;
import pages.PublicPaymentsMoscowPage;
import pages.RegionPage;

import java.util.ArrayList;
import java.util.ResourceBundle;

@Epic("Functional region tests")
@Feature("Region tests")
public class RegionPaymentTest extends TestBase {

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
        button.click(mainPage.getPayments());
        button.click(mainPage.getPublicPayments());
        publicPaymentsMoscowPage.chooseRegion(regionPage.getStPetersburgRegion());

        actualPublicPaymentsList = publicPaymentStPetersburgPage
                .getAllPeterburgPublicPayments(publicPaymentStPetersburgPage.getAllStPeterburgPublicPayments());

        Assert.assertFalse(actualPublicPaymentsList.contains(PublicPaymentsMoscow), "Public payment Moscow exist on the public payment St. Petersburg page");
    }
}
