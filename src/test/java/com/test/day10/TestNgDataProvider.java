package com.test.day10;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/3 21:28
 */
public class TestNgDataProvider {


    @Test(dataProvider = "data")
    public void test(String username,String password){
        System.out.println("username = " + username + ", password = " + password);
    }

    @DataProvider(name = "data") //如果DataProvider注解不给name值，被调用时可以直接提供name值
    public Object[][] datas(){
        Object[][] datas = {
                {"a","123"},
                {"b","456"},
                {"c","789"}
        };
        return datas;
    }

    @Test(dataProvider = "da")
    public void test2(String username){
        System.out.println("username = " + username);
    }

    @DataProvider(name = "da") //如果DataProvider注解不给name值，被调用时可以直接提供name值
    public Object[] data(){
        Object[] datas = {"a", "b", "c","789"};
        return datas;
    }

    @Test(dataProvider = "d")
    public void test3(Teacher t){
        System.out.println(t.getName()+t.getSex()+t.getAge());
    }

    @DataProvider(name = "d") //如果DataProvider注解不给name值，被调用时可以直接提供name值
    public Object[] data2(){
        Teacher t1 = new Teacher("laoma",50,"man");
        Teacher t2 = new Teacher("laoli",60,"man");
        Teacher t3 = new Teacher("laowang",70,"man");
        Object[] datas = {t1,t2,t3};
        return datas;
    }

    @Test(dataProviderClass = DataProviderClass.class,dataProvider = "data")
    public void test5(String username){
        System.out.println("username = " + username);}

}
