package com.tinkoff.steps;

import io.qameta.allure.Step;
import model.request.LoginRequest;
import model.response.LoginResponse;
import utils.RequestSender;
import utils.Urls;

public class LoginSteps {
    private RequestSender reqSender;

    public LoginSteps() {
        reqSender = new RequestSender();
    }

    /**
     * Get authorization token
     *
     * @param loginRequest login body for request
     * @return LoginResponse, response's login body
     */

    @Step("Get authorization token")
    public LoginResponse getAuthorizationToken(LoginRequest loginRequest) {

        LoginResponse loginResponse;

        loginResponse = reqSender.sendPOSTRequest(
                Urls.getAccountCheckPasswordSiignInUrl(),
                loginRequest,
                LoginResponse.class
        );

        return loginResponse;
    }
}
