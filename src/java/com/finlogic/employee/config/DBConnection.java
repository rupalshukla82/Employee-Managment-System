<<<<<<< HEAD
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
=======

//package com.finlogic.employee.config;
//import java.sql.*;

/**
 *
 * @author abhi
 */

>>>>>>> 56bed85 (Update DBConnection to use environment variables and new WAR file)
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


package com.finlogic.employee.config;

import java.sql.*;


public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Read database info from Railway environment variables
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASS");

            // Build JDBC URL dynamically
            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?sslMode=VERIFY_IDENTITY";

            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            conn = DriverManager.getConnection(url, dbUser, dbPass);

            System.out.println("DBConnection successful!");

        } catch (Exception e) {
            System.out.println("ERROR in DBConnection: " + e);
            e.printStackTrace();
        }

        return conn;
    }
}
