package com.lemon.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.base.BaseCase;
import com.lemon.env.Constants;
import com.lemon.env.GlobalEnvironment;
import com.lemon.until.ExcelData;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import static io.restassured.RestAssured.given;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/9/7 17:02
 */
public class LemonRegister extends BaseCase {

    List<ExcelData> excelData;


    @BeforeClass
    public void setup(){
        this.excelData = getData(0,1);

    }



    @DataProvider(name = "")
    public Object[] getData(){
        /*
        提取注册数据
         */

        return excelData.toArray();
    }

    @Test(dataProvider = "getData")
    public void testRegister(ExcelData excelData) throws JsonProcessingException, FileNotFoundException {

        if (excelData.getCase_id()==1){
            GlobalEnvironment.envVariable.put("phone_investor",randomPhoneNumber());
            excelData = singCaseReplace(excelData);
        }else if (excelData.getCase_id()==2){
            GlobalEnvironment.envVariable.put("phone_borrower",randomPhoneNumber());
            excelData = singCaseReplace(excelData);
        }else if (excelData.getCase_id()==3){
            GlobalEnvironment.envVariable.put("phone_admin",randomPhoneNumber());
            excelData = singCaseReplace(excelData);
        }

        Map params = jsonToMap(excelData.getData());
        Map headers = jsonToMap(excelData.getHeaders());
        Map expect = jsonToMap(excelData.getExpect());
        Map sqlCheck = jsonToMap(excelData.getSqlCheck());

        String filePath = logCat(excelData.getModule(),excelData.getCase_id());

        Response res =
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        log().all().
                        body(params).
                        headers(headers).
                when().//执行的操作（get、post请求）
                        post(excelData.getAddress()).
                then().log().all().
                        extract().response();//执行的断言操作

        assertExpect(expect,res);

        //数据库断言
        if (sqlCheck != null) { assertDatabase(sqlCheck);}


        //将接口信息作为附件返回到Allure
        Allure.addAttachment(excelData.getModule()+"接口响应信息",new FileInputStream(filePath));


    }




}
