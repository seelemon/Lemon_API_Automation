package com.test.day12;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/23 17:46
 */
public class AssertTest {
    String phoneNum;
    Map<String,Object> expect;
    Map<String,Object> param;

    @Test(dataProvider ="register" )
    public void testRegister (ExcelData excelData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();//将json对象转成map
        if (excelData.getExpect().contains("#phone#")){
            excelData.setExpect(replaceStr(excelData.getExpect(),randomPhoneNumber()));
            this.expect = objectMapper.readValue(excelData.getExpect(), Map.class);
            System.out.println(expect);
        }else{
            this.expect = objectMapper.readValue(excelData.getExpect(), Map.class);
            System.out.println(expect);
        }
        if (excelData.getData().contains("#phone#")){
            excelData.setData(replaceStr(excelData.getData(),randomPhoneNumber()));
            this.param = objectMapper.readValue(excelData.getData(), Map.class);
            System.out.println(param);
        }else{
            this.param = objectMapper.readValue(excelData.getData(), Map.class);
            System.out.println(param);
        }
        Map<String,Object> param = objectMapper.readValue(excelData.getData(), Map.class);
        System.out.println(param);
        Map<String,Object> headers = objectMapper.readValue(excelData.getHeaders(), Map.class);
        System.out.println(headers);

        Response res =
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        body(param).
                       headers(headers).
                        when().//执行的操作（get、post请求）
                        post("http://api.lemonban.com/futureloan"+excelData.getAddress()).
                        then().log().all().extract().response();

        
    }

    @DataProvider(name = "register")
    public Object[] getRegisterData(){
        File file = new File("src\\test\\resources\\futureloan_API_Test cases_v2.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setStartSheetIndex(0);
        importParams.setSheetNum(1);
        //easyPOI读取Excel,第一个参数为文件路径，第二个为映射的类，第三个为importparam对象
        List<ExcelData> datas = ExcelImportUtil.importExcel(file,ExcelData.class,importParams);
        return  datas.toArray();
    }

    public String randomPhoneNumber(){
        String num = "131";
        int i =1;
        int ranNum =0;
        Random random = new Random();
        while (i<9)
        {ranNum = random.nextInt(9);
        num = num + ranNum;
        i++;
        }
        return num;
    }

    public String replaceStr(String sourceStr,String relaceStr){
        String regex = "#(.*)#";//定义正则表达式
        Pattern pattern = Pattern.compile(regex);//通过正则表达式编译一个匹配器pattern出来
        Matcher matcher = pattern.matcher(sourceStr);//匹配参数，需要传入一个想要匹配的字符串
        String findStr = "";
        while (matcher.find()){ findStr = matcher.group(0);}//;连续匹配，将最终匹配的字符串取出来
        System.out.println(findStr);
        return sourceStr.replace(findStr,relaceStr);//替换最终字符串
    }


    
}

