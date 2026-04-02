/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abhi
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/AdminLoginServlet"})
public class AdminLoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Username = request.getParameter("username");
        System.out.println("username is:"+Username);
        String Password = request.getParameter("password");
        System.out.println("password is:"+Password);
        
        try{
            Connection conn = DBConnection.getConnection();
            
            String q = "select *from adminlogin where username=? and password=? ";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1,Username);
            ps.setString(2,Password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                HttpSession session = request.getSession();
                session.setAttribute("username", Username);
                
               response.sendRedirect("AdminDashboardServlet");
                
            }
            else{
               response.setContentType("text/html");
                response.getWriter().println(
                "<script>" +
                        "alert('no username or password found');" 
                        +
                          "window.location.href='AdminLogin.html';" 
                        +
                        "</script>"
                );          
                 
            }
  
        }catch(Exception e){
            System.out.println("Error is:"+e); 
           response.sendRedirect("AdminLogin.html");
        }
               
       
    }

       @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("AdminLogin.html");
    }


}
