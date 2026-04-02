/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;

import com.finlogic.employee.DAO.EmployeeDao;
import com.finlogic.employee.config.DBConnection;
import com.finlogic.employee.model.Employee;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author abhi
 */
@WebServlet("/AddServlet")
@MultipartConfig(maxFileSize = 5242880) //max file size 5MB
public class AddEmpServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try{
           
             Part resumePart = request.getPart("resume");
             byte[] resume = null;
             if(resumePart !=null && resumePart.getSize() >0)
             {
                 resume = resumePart.getInputStream().readAllBytes();
             }        
        Employee emp = new Employee(
         request.getParameter("full_name"),
         request.getParameter("username"),
         request.getParameter("password"),
         request.getParameter("email"),
         request.getParameter("phone"), 
         request.getParameter("city"),
         request.getParameter("position"),
         request.getParameter("department"),
         resume
//         request.getPart("resume").getInputStream();
        
        );
        
        
        String Source = request.getParameter("source");
        
         EmployeeDao  dao = new EmployeeDao();
         dao.addEmployee(emp);
         
         //chek size of file which is less than 2MB
         if(resumePart.getSize() > 2*1024*1024){
             response.setContentType("text/html");
             response.getWriter().println(
             "<script>"+
                     "alert('file size must be less than 2MB!');"
                     +
                     "window.history.back();"
                     +
                     "</script>"
             );
             
             return;
         }
         
         if(Source != null && Source.equals("admin")){
             response.setContentType("text/html");
             response.getWriter().println(
                  "<script>" + 
                         
                         "alert('Employee Added Scuessfully');" 
                        +
                          "window.location.href='AdminDashboardServlet'"
                        +
                          
                  "</script>" 
                     
                     );
         }
         else{
             
               response.setContentType("text/html");
             response.getWriter().println(
                  "<script>" + 
                         
                         "alert('Employee Added Scuessfully');" 
                        +
                          "window.location.href='login.html'"
                        +
                          
                  "</script>" 
                     
                     );
             
         }
        
          }catch(Exception e){
//            System.out.println("e is:"+e);
              e.printStackTrace();
        }
        
    }
       

}
