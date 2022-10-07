package com.woebot.api.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestExecutor {

    /** +
    *
    * @param request
    * @return
    * @throws Exception
    * */
    public Response executeRequest(Request req) throws Exception{

        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpecification = generateRequestSpecification(req);
        Response res = given().spec(requestSpecification).request(req.getRequestType());
        return res;

    }

    /** +
    *
    * @param request
    * @return
    *
    * */

    private RequestSpecification generateRequestSpecification(Request req) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        if(req.getRequestBody() != null){
            builder.setBody(req.getRequestBody());
        }

        if(req.getApiPath() != null){
            builder.setBasePath(req.getApiPath());
        }

        if(req.getBaseUrl() != null){
            builder.setBaseUri(req.getBaseUrl());
        }

        if(req.getContentType() != null){
            builder.setContentType(req.getContentType());
        }

        if(req.getHeaders() != null){
            builder.addHeaders(req.getHeaders());
        }

        if(req.getQueryParams() != null){
            builder.addQueryParams(req.getQueryParams());
        }

        RequestSpecification requestSpecification = builder.build();
        return requestSpecification;

    }
}
