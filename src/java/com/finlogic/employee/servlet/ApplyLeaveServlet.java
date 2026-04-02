/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abhi
 */
@WebServlet(name = "ApplyLeaveServlet", urlPatterns = {"/ApplyLeaveServlet"})
public class ApplyLeaveServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("Username") == null){
            response.sendRedirect("login.html");
            return;
        }
        
        String username = (String) session.getAttribute("Username");
        
        //reading data 
        
        String leaveType = request.getParameter("leave_type");
        String fromDate = request.getParameter("from_date");
        String toDate = request.getParameter("to_date");
        String reason = request.getParameter("reason");
        
        try{
            
            Connection conn = DBConnection.getConnection();
            
            //getting employee id & name

            String empQ = "select id, full_name from empsignup where username=?";
            PreparedStatement ps = conn.prepareStatement(empQ);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                int empID = rs.getInt("id");
                String empName = rs.getString("full_name");
                
//                Insert Leave Application

            String q = "insert into leaves (emp_id,emp_name,leave_type,from_date,to_date,reason) values(?,?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(q);
            ps1.setInt(1, empID);
            ps1.setString(2, empName);
            ps1.setString(3, leaveType);
            ps1.setString(4, fromDate);
            ps1.setString(5, toDate);
            ps1.setString(6, reason);
            
            boolean status = ps1.executeUpdate()>0;
            
            if(status){
                response.setContentType("text/html");
                response.getWriter().println(
                        "<script>"+
                                "alert('leave applied sucessfully! wait for admin approval ');"+
                                "window.location.href='EmpDashboardServelet';"+
                                "</script>"
                );
            }
            else{
                response.setContentType("text/html");
                    response.getWriter().println(
                        "<script>" +
                        "alert('Something went wrong! Try again.');" +
                        "window.history.back();" +
                        "</script>"
                    );
            }
            
           
            }
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }

     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("ApplyLeave.html");
    }
}
