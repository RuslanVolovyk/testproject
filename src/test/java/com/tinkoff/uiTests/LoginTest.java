package com.tinkoff.uiTests;

import com.tinkoff.TestBase;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.request.LoginRequest;
import model.response.LoginResponse;
import org.testng.annotations.Test;
import com.tinkoff.steps.LoginSteps;


@Epic("Login")
@Feature("login tests")
public class LoginTest extends TestBase {

    @Story("login")
    @Test(description = "login")
    public void loginTest() {
        LoginRequest loginRequest;
        LoginResponse loginResponse;
        LoginSteps steps = new LoginSteps();

        loginRequest = new LoginRequest("uqnnmqja@yomail.info", "a95cc30c-4f14-482c-9948-87006b0cf17f",
                "TeSt@09111");

        loginResponse = steps.getAuthorizationToken(loginRequest);

        String token = loginResponse.verificationCode;

        windows.navigate("https://ca-kordamentha.andersenlab.com/corona/welcome/sun_1");
        button.click(myPage.getSigInButton());
        fields.type(myPage.getEmailInput(), "uqnnmqja@yomail.info");
        fields.type(myPage.getPasswordInput(), "TeSt@09111");
        button.click(myPage.getSigIn());

        fields.type(myPage.getVerificationCodeInput(), token);
        button.click(myPage.getVerifyButton());
    }
}
