package com.test.day04;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/4/18 22:52
 */
public class Phone {
    private String brand;
    private int price;

//    public void call(){
//        System.out.println("2G信号打电话");
//    }
//
//    public  void sendMessage(){
//        System.out.println("老年牌九键发短信");
//    }

   public void setBrand(String brand){
       this.brand=brand;

   }

   public String getBrand(){
    return brand;
   }

   public void setPrice(int price){
       if(price > 10000 | price < 2000){
           System.out.println("当前价格为："+price +"不符合当前市场价，谨防诈骗");
           return;
       }
       this.price=price;
   }
   public int getPrice(){
       return price;
   }

}
