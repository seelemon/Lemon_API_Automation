package com.lemon.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.base.BaseCase;
import com.lemon.env.GlobalEnvironment;
import com.lemon.until.ExcelData;
import com.lemon.until.JDBCUtils;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/9/12 21:30
 */
public class LemonAudit extends BaseCase {


    List<ExcelData> excelData;


    @BeforeClass
    public void setup() {

        this.excelData = getData(6, 1);
        this.excelData = paramsRepalc(excelData);

    }


    @DataProvider(name = "")
    public Object[] getData() {
        /*
        提取充值用例数据
         */

        return excelData.toArray();
    }

    @Test(dataProvider = "getData")
    public void testAudit(ExcelData excelData) throws JsonProcessingException, FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map params = jsonToMap(excelData.getData());
        Map headers = jsonToMap(excelData.getHeaders());
        Map expect = jsonToMap(excelData.getExpect());
        Map sqlCheck = jsonToMap(excelData.getSqlCheck());
        String filePath = logCat(excelData.getModule(),excelData.getCase_id());


        Response res =
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        headers(headers).
                        body(params).
                when().//执行的操作（get、post请求）
                        patch(excelData.getAddress()).
                then().log().all().
                        extract().response();//执行的断言操作

        assertExpect(expect,res);
        //数据库断言
        if (sqlCheck != null){
            assertDatabase(sqlCheck);
        }
        //将接口信息作为附件返回到Allure
        Allure.addAttachment(excelData.getModule()+"接口响应信息",new FileInputStream(filePath));
    }
}
