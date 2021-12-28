package com.lemon.base;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.env.Constants;
import com.lemon.env.GlobalEnvironment;
import com.lemon.until.ExcelData;
import com.lemon.until.*;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/9/6 22:01
 */
public class BaseCase {

    @BeforeTest
    public void setUpGlobalStep() throws FileNotFoundException {
        RestAssured.baseURI = Constants.BASH_URL;
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        //将日志保存到指定目录当中
//        PrintStream fileOutPut = new PrintStream("src/test/java/com/lemon/log/log.log");
//        RestAssured.filters(new RequestLoggingFilter(fileOutPut),new ResponseLoggingFilter(fileOutPut));
    }

    public List<ExcelData> getData(int index,int totalSheet){
        File file = new File(Constants.V2_CASE);
        ImportParams importParams = new ImportParams();
        importParams.setStartSheetIndex(index);
        importParams.setSheetNum(totalSheet);
        //easyPOI读取Excel,第一个参数为文件路径，第二个为映射的类，第三个为importparam对象
        List<ExcelData> datas = ExcelImportUtil.importExcel(file,ExcelData.class,importParams);
        return  datas;
    }

    public static String randomPhoneNumber(){
        /*
        1.生成随机手机号
        2.去数据库查询这个手机号是否被注册，如果数量为0，则未被注册
        3.如果返回的数据为1，则继续循环生成另一个手机号
         */
        String num = "";
        String sql = "select count(*) from member where mobile_phone =";
        int i =1;
        int ranNum =0;
        Random random = new Random();
        long queryResult = 1;
        while (queryResult == 1){
            num = "131";
            while (i<9)
            {ranNum = random.nextInt(9);
                num = num + ranNum;
                i++;
            }
           queryResult = (long) JDBCUtils.queryField(sql+num);
        }
        return num;
    }

    public String replaceStr(String sourceStr){

        if(sourceStr == null){
            return sourceStr;
        }
        String regex = "\\{\\{(.*?)\\}\\}";//定义正则表达式
        Pattern pattern = Pattern.compile(regex);//通过正则表达式编译一个匹配器pattern出来
        Matcher matcher = pattern.matcher(sourceStr);//匹配参数，需要传入一个想要匹配的字符串
        String findStr = "";
        Object firstGroupStr="";
        while (matcher.find()){
            findStr = matcher.group(0);//;连续匹配，将最终匹配的字符串取出来
            firstGroupStr = matcher.group(1);
            System.out.println(findStr);
            System.out.println(firstGroupStr);
            Object replaceEnvStr = GlobalEnvironment.envVariable.get(firstGroupStr);//取出（）中的数据
            sourceStr =  sourceStr.replace(findStr,replaceEnvStr+"");//替换最终字符串
        }
        return sourceStr;
    }

    public List<ExcelData> paramsRepalc(List<ExcelData> excelData){
        /*
        封装需要替换的参数：url、headers、params、expect
         */
        for (ExcelData data:excelData){

            String headers = replaceStr(data.getHeaders());
            data.setHeaders(headers);

           String url = replaceStr(data.getAddress());
            data.setAddress(url);


            String params = replaceStr(data.getData());
            data.setData(params);

            String expect = replaceStr(data.getExpect());
            data.setExpect(expect);

            String sqlCheck = replaceStr(data.getSqlCheck());
            data.setSqlCheck(sqlCheck);

        }

        return excelData;
    }

    public ExcelData singCaseReplace(ExcelData excelData){
        /*
        封装需要替换的参数：url、headers、params、expect
         */
            String headers = replaceStr(excelData.getHeaders());
            excelData.setHeaders(headers);

            String url = replaceStr(excelData.getAddress());
            excelData.setAddress(url);


            String params = replaceStr(excelData.getData());
            excelData.setData(params);

            String expect = replaceStr(excelData.getExpect());
            excelData.setExpect(expect);

            String sqlCheck = replaceStr(excelData.getSqlCheck());
            excelData.setSqlCheck(sqlCheck);

        return excelData;
    }

    public void assertExpect(Map expect, Response res){

        Set<Map.Entry<String,Object>> entries = expect.entrySet();
        for (Map.Entry<String,Object> expectMap:entries){
            Object expectedValue = expectMap.getValue();
            try {
                if ((expectedValue instanceof Float) || (expectedValue instanceof Double) ){
                    BigDecimal bigDecimal = new BigDecimal(expectedValue.toString());//将小数类型转换为bigdecimal类型
                    Assert.assertEquals(res.path(expectMap.getKey()),bigDecimal);
                    System.out.println("接口断言成功，断言内容：：实际结果：" + res.path(expectMap.getKey()) + " " + "预期结果：" + expectMap.getValue());
                }else {
                    Assert.assertEquals(res.path(expectMap.getKey()), expectMap.getValue());
                    System.out.println("接口断言成功，断言内容：：实际结果：" + res.path(expectMap.getKey()) + " " + "预期结果：" + expectMap.getValue());
                }
            }
            catch (Exception e){
                System.out.println("断言失败：失败原因：："+e);
            }
        }
    }

    public void assertDatabase(Map expect) {

        Set<Map.Entry<String, Object>> entries = expect.entrySet();
        for (Map.Entry<String, Object> expectMap : entries) {
            Object expectedValue = expectMap.getValue();
            try {
                Object queryResult = JDBCUtils.queryField(expectMap.getKey());
                if (queryResult instanceof BigDecimal) {
                    BigDecimal bigDecimalExpect = new BigDecimal(expectedValue.toString());//将小数类型转换为bigdecimal类型
                    System.out.println("数据库断言预期结果：："+bigDecimalExpect);
                    System.out.println("数据库断言实际结果：："+queryResult);
                    Assert.assertEquals(queryResult, bigDecimalExpect);

                } else if (expectedValue instanceof Integer){
                    Long expectValue = new Long(expectMap.getValue().toString());
                    Assert.assertEquals(queryResult,expectValue);
                }
                else {
                    Assert.assertEquals(queryResult, expectMap.getValue());
                }
                System.out.println("数据库断言成功，断言内容：：实际结果：" + queryResult + " " + "预期结果：" + expectMap.getValue());
            } catch (Exception e) {
                System.out.println("断言失败：失败原因：：" + e);
            }
        }
    }

    public Map jsonToMap(String mapStr){
        if (mapStr == null){
            return null;
        }
        Map mapData =null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
          mapData = objectMapper.readValue(mapStr, Map.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapData;
    }

    public String logCat(String interFace,int Case_id)  {
        /*
        开关控制是否将日志打印到target目录下
         */
        String filePath = "";
        if (!Constants.IS_DEBUGLOG) {

            String dirPath = "target/log/" + interFace;
            File file = new File(dirPath);

            if (!file.isDirectory()) {
                file.mkdirs();
            }

            PrintStream fileOutPutStream = null;
            try {
                filePath = dirPath +"/" + interFace+"_"+ Case_id + ".log";
                fileOutPutStream = new PrintStream(new File(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            RestAssured.config =
                    RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(fileOutPutStream));

        }

        return filePath;
    }

    public void paramsV3Config(){
        /*
        生成v3版本必要的请求参数
        */
        long timestamp = System.currentTimeMillis();
        String interceptStr = GlobalEnvironment.envVariable.get("investor_token").toString().substring(0,50);
    }

}
