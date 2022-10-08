package com.woebot.api.dataprovider;

import com.woebot.api.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class TestDataprovider {

    @DataProvider
    public Object[][] createNewUser() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")
                +"/src/test/resources/testdata/TestData.xlsx","NewUser");

        return (testObjArray);


    }

    @DataProvider
    public Object[][] newMessage() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")
                +"/src/test/resources/testdata/TestData.xlsx","NewMessage");

        return (testObjArray);


    }
}
