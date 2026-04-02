///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.finlogic.employee.config;
import java.sql.*;
import java.util.*;
///**
// *
// * @author abhi
// */
//
//
//public class DBConnection {
//  
//private static final String url = "jdbc:mysql://2udHaNYZZgggqGU.root:hvHPkqxFzK5bD6IT@gateway01.ap"
//        + "-southeast-1.prod.aws.tidbcloud.com:4000/emplogin?sslMode=VERIFY_IDENTITY";
//private static final String user = "2udHaNYZZgggqGU.root";
//private static final String password = "hvHPkqxFzK5bD6IT";
//
//Connection conn = null;
//
//public static Connection getConnection(){
//    Connection conn = null;
//    
//    try{
//   
//    Class.forName("com.mysql.cj.jdbc.Driver");
//    conn = DriverManager.getConnection(url,user,password);
//        System.out.println("DBConnection done");
//}
//catch(Exception e){
//    System.out.println("ERROR IS:"+e);
//}
//    return conn;
//}
//
//}

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("DBConnection done");

        } catch (Exception e) {
            System.out.println("ERROR IS:" + e);
        }

        return conn;
    }
}
