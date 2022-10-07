package com.woebot.api.test;


import com.woebot.api.core.ApiMethodTypes;
import com.woebot.api.core.Request;
import com.woebot.api.core.RequestBuilder;
import com.woebot.api.core.RequestConstants;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.ITestContext;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    Properties properties = new Properties();

    public BaseTest() {

        try{
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream("config.properties"));
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public Request buildApiRequest(Map<String,Object> queryParams,
                                   String endpoint,String apiMethod,String apiRequestBody){

        Method requestType;
        if (apiMethod.toLowerCase().equalsIgnoreCase(ApiMethodTypes.POST)){
            requestType = Method.POST;
        }else if(apiMethod.toLowerCase().equalsIgnoreCase(ApiMethodTypes.PUT)){
            requestType = Method.PUT;
        }else if(apiMethod.toLowerCase().equalsIgnoreCase(ApiMethodTypes.GET)){
            requestType = Method.GET;
        }else{
            requestType = Method.DELETE;
        }
        return new RequestBuilder().With(($) -> {
            $.baseUrl = properties.getProperty("api.url");
            $.apiPath = endpoint;
            $.queryParameters = queryParams;
            $.requestType = requestType;
            $.requestBody = apiRequestBody;
        }).buildRequestObject();

    }

    protected ITestContext getITestContextAttributes(ITestContext iTestContext,
                                                     String testDescription,
                                                     Request request, Response response){
        iTestContext.setAttribute(RequestConstants.TEST_DESCRIPTION,testDescription);
        iTestContext.setAttribute(RequestConstants.REQUEST,request);
        iTestContext.setAttribute(RequestConstants.RESPONSE,response);
        iTestContext.setAttribute(RequestConstants.STATUS_CODE,response.getStatusCode());
        iTestContext.setAttribute(RequestConstants.RESPONSE_TIME,response.getTimeIn(TimeUnit.MILLISECONDS));

        return iTestContext;
    }
}
