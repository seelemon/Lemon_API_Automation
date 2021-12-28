package com.test.day04;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/4/18 23:06
 */
public class PhoneTest {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.setBrand("Huawei love++");
        phone.setPrice(1000);
        System.out.println("您正在准备购买的手机为："+ phone.getBrand());
    }
}
