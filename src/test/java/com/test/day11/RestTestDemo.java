package com.test.day11;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/16 18:26
 */
public class RestTestDemo {

    @Test
    public void requestGet (){

                HashMap<String,Object> map = new HashMap<>();
                map.put("name","张三");
                map.put("age","25");

                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        queryParams(map).
                when().//执行的操作（get、post请求）
                        get("http://httpbin.org/get").
                then().log().all();//执行的断言操作
    }

    @Test
    public void requestPost1 (){

                /*
                表单参数post请求
                 */
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        formParam("user","lsp").
                        formParam("pwd","123").
                        contentType("application/x-www-form-urlencoded;Charset=utf-8").
                when().//执行的操作（get、post请求）
                    post("http://httpbin.org/post").
                then()
                        .log().all();//执行的断言操作
    }

    @Test
    public void requestPost2 (){

                /*
                json post请求
                 */
        String jsonStr = "{ \"mobile_phone\":13112341238,\"pwd\":\"12345678\" }";
        given().//前置条件，包括请求头、请求参数、请求体、cookie
                contentType("application/json;charset=utf-8").
                body(jsonStr).
                when().//执行的操作（get、post请求）
                post("http://httpbin.org/post").
                then()
                .log().all();//执行的断言操作
    }

    @Test
    public void requestPost3 (){

                /*
                xml post请求
                 */
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n" +
                "<suite name=\"接口1\">\n" +
                "    <test name=\"C:/Users/SEMON/IdeaProjects/Java_project\">\n" +
                "        <classes>\n" +
                "            <class name=\"com.test.day09.TestNgParameter\"></class>\n" +
                "            <parameter name=\"type\" value=\"chrome\"></parameter>\n" +
                "        </classes>\n" +
                "    </test>\n" +
                "</suite>";
        given().//前置条件，包括请求头、请求参数、请求体、cookie
                contentType("text/xml;charset=utf-8").
                body(xmlStr).
                when().//执行的操作（get、post请求）
                post("http://httpbin.org/post").
                then()
                .log().all();//执行的断言操作
    }

    @Test
    public void requestPost4 (){

                /*
                多文件参数post请求
                 */
        given().//前置条件，包括请求头、请求参数、请求体、cookie
                contentType("multipart/form-data;Charset=utf-8").
                multiPart(new File("C:\\Users\\SEMON\\Pictures\\QQ浏览器截图\\QQ浏览器截图20200516164113.png")).
                when().//执行的操作（get、post请求）
                post("http://httpbin.org/post").
                then()
                .log().all();//执行的断言操作
    }


    @Test
    public void lenmonRegister (){

        HashMap<String,Object> map = new HashMap<>();
        map.put("mobile_phone","13112341235");
        map.put("pwd","12345678");
        map.put("type",1);
            Response res =
                given().//前置条件，包括请求头、请求参数、请求体、cookie
                        body(map).
                        contentType("application/json;Charset=utf-8").
                        header("X-Lemonban-Media-Type","lemonban.v2").
                when().//执行的操作（get、post请求）
                        post("http://api.lemonban.com/futureloan/member/register").
                then().extract().response();//执行的断言操作
        System.out.println(res.asString());


         }


    @Test
    public void lenmonLogin (){

        HashMap<String,Object> map = new HashMap<>();
        map.put("mobile_phone","13112341235");
        map.put("pwd","12345678");
        String jsonStr = "{ \"mobile_phone\":13112341238,\"pwd\":\"12345678\" }";

        Response res = given().//前置条件，包括请求头、请求参数、请求体、cookie
                body(jsonStr).
                contentType("application/json;Charset=utf-8").
                header("X-Lemonban-Media-Type","lemonban.v2").
                when().//执行的操作（get、post请求）
                post("http://api.lemonban.com/futureloan/member/login").
                then().log().all().extract().response();
                String tokenValue = res.path("data.type").toString();
        System.out.println(tokenValue);
    }

    }

