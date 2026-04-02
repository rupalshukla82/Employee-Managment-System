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
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abhi
 */
@WebServlet(name = "ViewMyLeavesServlet", urlPatterns = {"/ViewMyLeavesServlet"})
public class ViewMyLeavesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
       
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("Username") == null){
            response.sendRedirect("login.html");
            return;
        }
        
        String username = (String) session.getAttribute("Username");
        
        try{
            
            Connection conn = DBConnection.getConnection();
            String empQ = "select id from empsignup where username =?";
            PreparedStatement Eps = conn.prepareStatement(empQ);
            Eps.setString(1, username);
            ResultSet empRs  = Eps.executeQuery();
            
            if(empRs.next()){
                int empId = empRs.getInt("id");
                
                String q = "select * from leaves where emp_id=? order by applied_at desc";
                PreparedStatement ps = conn.prepareStatement(q);
                ps.setInt(1, empId);
                ResultSet rs = ps.executeQuery();
                
                List<Map<String,String>> leavelist = new ArrayList<>();
                while(rs.next()){
                    Map<String, String> leave = new HashMap<>();
                    leave.put("id", rs.getString("id"));
                    leave.put("leave_type", rs.getString("leave_type"));
                    leave.put("from_date",  rs.getString("from_date"));
                    leave.put("to_date",    rs.getString("to_date"));
                    leave.put("reason",     rs.getString("reason"));
                    leave.put("status",     rs.getString("status"));
                    leave.put("applied_at", rs.getString("applied_at"));
                    leavelist.add(leave);
                }
                request.setAttribute("leaveList", leavelist);
                request.getRequestDispatcher("myLeaves.jsp")
                       .forward(request, response);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }

  

}
