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

/**
 *
 * @author abhi
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("Npassword");
        String ConfirmPassword = request.getParameter("confirmPassword");
        
        if(!newPassword.equals(ConfirmPassword)){
           response.setContentType("text/html");
           response.getWriter().println(
           
                   "<script>"+
                           "alert('confirmpassword & newPassword is must be same');"
                   +
                           "window.history.back();"
                   +
                     "</script>"
           );
           return;
        }
        try{
            Connection conn = DBConnection.getConnection();
            
            String q = "select * from empsignup where username= ? and email = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ps.setString(2, email);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                String updateQ = "update empsignup set password=? where username=?";
                PreparedStatement ps1 = conn.prepareStatement(updateQ);
                ps1.setString(1, newPassword);
                ps1.setString(2, username);
                
                ps1.executeUpdate();
                
                response.setContentType("text/html");
                response.getWriter().println(
                "<script>"+
                        "alert('password reset sucessfully! Login please');"
                        +
                        "window.location.href='login.html';"
                        +
                        "</script>"
                );
                
            }
            else{
                response.setContentType("text/html");
                response.getWriter().println(
                "<script>"+
                        "alert('No user found check username or email');"
                        +
                        "window.history.back();"
                        +
                        "</script>"
                );     
                }
            
        }
        catch(Exception e){
            System.out.println("Error is :"+e);
        }
        
    }

   
}
