package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSender {

    /**
     * Process POST request with or without request's body
     *
     * @param url             string value of request's url
     * @param body            request's body
     * @param responseAsClass expected response class
     * @return response's object which type like as responseAsClass
     */
    public synchronized <T> T sendPOSTRequest(String url, Object body, Class<T> responseAsClass) {
        Response response;

        if (body != null) {
            response = createHeaderWithoutToken()
                    .body(body)
                    .when()
                    .post(url)
                    .then()
                    .log().status()
                    .log().body()
                    .extract().response();
        } else {
            response = createHeaderWithoutToken()
                    .when()
                    .post(url)
                    .then()
                    .log().status()
                    .log().body()
                    .extract().response();
        }

        return mapResponseToObject(response, responseAsClass);
    }



    /**
     * Create header for request, using token
     *
     * @return RequestSpecification - prepared request
     */
    private RequestSpecification createHeaderWithoutToken() {
        return given()
                .contentType(ContentType.JSON).relaxedHTTPSValidation()
                .request()
                .log().uri()
                .log().headers()
                .log().body();
    }


    /**
     * Cast response to expected class type
     *
     * @param response        response's body
     * @param responseAsClass expected response class
     * @return response's object which type like as responseAsClass
     */
    private <T> T mapResponseToObject(Response response, Class<T> responseAsClass) {
        T responseObject = null;
        try {
            responseObject = responseAsClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
        }
        if (!response.asString().equals("") && !response.asString().equals(" ")) {
            responseObject = response.as(responseAsClass);
        }
        return responseObject;
    }

}