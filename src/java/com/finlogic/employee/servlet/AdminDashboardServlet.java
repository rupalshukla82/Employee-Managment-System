/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author abhi
 */
@WebServlet(name = "AdminDashboardServlet", urlPatterns = {"/AdminDashboardServlet"})
public class AdminDashboardServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        
       if(session == null || session.getAttribute("username") == null){
            response.sendRedirect("AdminLogin.html");
            return;
        }
        
        String username = (String) session.getAttribute("username");
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        try{
            
            Connection conn = DBConnection.getConnection();
                  
           String q = "select * from adminlogin where username = ?";
             
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Connection empCon = DBConnection.getConnection();
                
                //total employee
               PreparedStatement ps1 = empCon.prepareStatement("select count(*) from empsignup");
               ResultSet rs1 = ps1.executeQuery();
               if(rs1.next()){
                   request.setAttribute("totalEmp", rs1.getInt(1) );
               }
               
               //total Resume Uploaded
               PreparedStatement ps2 = empCon.prepareStatement("select count(*) from empsignup where resume is not null");
               ResultSet rs2 = ps2.executeQuery();
               if(rs2.next()){
                   request.setAttribute("totalResume", rs2.getInt(1));
               }
               
               //Total Unique position
               PreparedStatement ps3 = empCon.prepareStatement("select count(distinct position) from empsignup");
               ResultSet rs3 = ps3.executeQuery();
               if(rs3.next()){
                   request.setAttribute("totalPosition", rs3.getInt(1));
               }
                 
               //Latest joined employee
               PreparedStatement ps4 = empCon.prepareStatement("select full_name, created_at from empsignup order by created_at  DESC LIMIT 1");
               ResultSet rs4 = ps4.executeQuery();
               if(rs4.next()){
                   request.setAttribute("latestEmp", rs4.getString("full_name"));
                   request.setAttribute("latestDate", rs4.getString("created_at"));
               }
               
               request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
               
            }
            else{
                response.sendRedirect("AdminLogin.html");
            }
        }
        catch(Exception e){
                System.out.println("Error is :"+e);
            }
        
    }

    
}
