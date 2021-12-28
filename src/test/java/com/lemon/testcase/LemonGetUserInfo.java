package com.lemon.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.base.BaseCase;
import com.lemon.env.GlobalEnvironment;
import com.lemon.until.ExcelData;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import jdk.nashorn.internal.ir.IfNode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/9/7 17:03
 */
public class LemonGetUserInfo extends BaseCase {

    List<ExcelData> excelData;
    Map<String,Object> params;

    @BeforeClass
    public void setup() {

        this.excelData = getData(2, 1);
        this.excelData = paramsRepalc(excelData);

    }


    @DataProvider(name = "")
    public Object[] getData() {
        /*
        提取用户信息数据
         */

        return excelData.toArray();
    }

    @Test(dataProvider = "getData")
    public void testGetUserInfo(ExcelData excelData) throws JsonProcessingException, FileNotFoundException {
        Map params = jsonToMap(excelData.getData());
        Map headers = jsonToMap(excelData.getHeaders());
        Map expect = jsonToMap(excelData.getExpect());
        Map sqlCheck = jsonToMap(excelData.getSqlCheck());
        String filePath =logCat(excelData.getModule(),excelData.getCase_id());

        Response res =
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        headers(headers).
                when().//执行的操作（get、post请求）
                        get(excelData.getAddress()).
                then().log().all().
                        extract().response();//执行的断言操作

        assertExpect(expect,res);
        //将接口信息作为附件返回到Allure
        Allure.addAttachment(excelData.getModule()+"接口响应信息",new FileInputStream(filePath));

    }
}
