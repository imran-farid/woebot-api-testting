package com.woebot.api.test;

import com.woebot.api.core.Request;
import com.woebot.api.core.RequestExecutor;
import com.woebot.api.dataprovider.TestDataprovider;
import com.woebot.api.utils.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WoebotApiIntegrationTests  extends BaseTest{

    RequestExecutor requestExecutor;

    @BeforeClass
    public void setup(){
        requestExecutor = new RequestExecutor();
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(priority=1,dataProvider = "createNewUser", dataProviderClass = TestDataprovider.class)
    public void verifyNewUserApi(ITestContext testContext, String testDescription,
                                 String apiRequestBody,String expectedStatusCode){

        Map<String,Object> queryParams = new HashMap<>();
        Request apiRequest = buildApiRequest(queryParams,"/user/new",
                "post",apiRequestBody);

        try{

            Response response =requestExecutor.executeRequest(apiRequest);
            testContext = getITestContextAttributes(testContext,testDescription,apiRequest,response);
            assertThat("Status code match:",response.getStatusCode(),
                    is(equalTo(Integer.valueOf(expectedStatusCode))));

        }catch (Exception e){
            e.printStackTrace();
        }


    }

//    @Test(priority=2,dataProvider = "newMessage", dataProviderClass = TestDataprovider.class)
//    public void verifyNewMessageApi(ITestContext testContext, String testDescription,
//                                 String apiRequestBody,String expectedStatusCode){
//
//        Map<String,Object> queryParams = new HashMap<>();
//        Request apiRequest = buildApiRequest(queryParams,"/message/new",
//                "post",apiRequestBody);
//
//        try{
//
//            Response response =requestExecutor.executeRequest(apiRequest);
//            testContext = getITestContextAttributes(testContext,testDescription,apiRequest,response);
//            assertThat("Status code match:",response.getStatusCode(),
//                    is(equalTo(Integer.valueOf(expectedStatusCode))));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
