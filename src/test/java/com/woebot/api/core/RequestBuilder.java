package com.woebot.api.core;

import io.restassured.http.Method;

import java.util.Map;
import java.util.function.Consumer;

public class RequestBuilder {

    public RequestBuilder() {
    }

    public Map<String,Object> queryParameters;
    public String apiPath;
    public Method requestType;
    public Map<String,String> headers;
    public String contentType;
    public String requestBody;

    public String baseUrl;


    public RequestBuilder With(Consumer<RequestBuilder> builderFunction){
        builderFunction.accept(this);
        return this;
    }

    public Request buildRequestObject(){
        return new Request(queryParameters,apiPath,baseUrl,requestType,headers,
                contentType,requestBody);
    }



}
