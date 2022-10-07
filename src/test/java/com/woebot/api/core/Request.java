package com.woebot.api.core;

import io.restassured.http.Method;
import lombok.Data;

import java.util.Map;

@Data
public class Request {
    private Map<String,Object> queryParams;
    private String apiPath;
    private String baseUrl;
    private Method requestType;
    private Map<String,String> headers;
    private String contentType;
    private String requestBody;

    public Request(Map<String, Object> queryParams, String apiPath,
                   String baseUrl, Method requestType,
                   Map<String, String> headers, String contentType,
                   String requestBody) {
        this.queryParams = queryParams;
        this.apiPath = apiPath;
        this.baseUrl = baseUrl;
        this.requestType = requestType;
        this.headers = headers;
        this.contentType = contentType;
        this.requestBody = requestBody;
    }


}
