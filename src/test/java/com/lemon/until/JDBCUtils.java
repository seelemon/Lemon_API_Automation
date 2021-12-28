package com.lemon.until;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/9/11 15:36
 */
public class JDBCUtils {


    public static Connection getConnection(){
        /*
        定义数据库连接参数
        MySql：jdbc:mysql://localhost:3306/DBName
         */

        String url = "jdbc:mysql://api.lemonban.com:3306/futureloan?useUnicode=true&characterEncoding=utf-8";
        String user = "future";
        String password = "123456";

        //定义数据库连接对象
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void updateDataBase(String sql){

        //获取数据库连接对象
        Connection connection = getConnection();
        //数据库新增操作
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //关闭数据库连接
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static List<Map<String,Object>> queryAllList(String sql){
        /*
        1、定义查询参数,
        2、查询多个返回数据
        3、MapListHandler()接收，
        4、结果保存到List<Map<String,Object>>中
         */
        //获取数据库连接对象
        Connection connection = getConnection();
        //数据库新增操作
        QueryRunner queryRunner = new QueryRunner();
        try {
            //第一个参数为数据库连接对象，第二个参数为参数为sql语句，第三个参数为ResultSetHandler（定义接收结果集）
            List<Map<String,Object>> multipleRecord = queryRunner.query(connection,sql,new MapListHandler());
            return multipleRecord;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //关闭数据库连接
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> queryAll(String sql) {
        /*
        1、定义查询参数,
        2、查询多个返回数据
        3、MapHandler()接收，
        4、结果保存到Map<String,Object>中
         */
        //获取数据库连接对象
        Connection connection = getConnection();
        //数据库新增操作
        QueryRunner queryRunner = new QueryRunner();
        try {
            //第一个参数为数据库连接对象，第二个参数为参数为sql语句，第三个参数为ResultSetHandler（定义接收结果集）
            Map<String, Object> singleRecord = queryRunner.query(connection, sql, new MapHandler());
            return singleRecord;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //关闭数据库连接
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public static Object queryField(String sql){
        /*
        1、定义查询参数,
        2、查询多个返回数据
        3、ScalarHandler<Object>接收，
        4、结果保存到Object中
         */
        //获取数据库连接对象
        Connection connection = getConnection();
        //数据库新增操作
        QueryRunner queryRunner = new QueryRunner();
        try {
            //第一个参数为数据库连接对象，第二个参数为参数为sql语句，第三个参数为ResultSetHandler（定义接收结果集）
           Object singlefield = queryRunner.query(connection,sql,new ScalarHandler<Object>());
            System.out.println(singlefield);
            return singlefield;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //关闭数据库连接
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        //String sql = "insert into member VALUES(100000112,'lemon','25D55AD283AA400AF464C76D713C07AD','13312341235',1,1000.00,'2021-08-11 14:20:10')";
        //String sql = "select * from member limit 5";
        //queryAllList(sql);
        //String sql = "select * from member where id ='100000112'";
        //queryAll(sql);
        String sql = "select count(*) from member where id ='100000112'";
        queryField(sql);
    }
}
