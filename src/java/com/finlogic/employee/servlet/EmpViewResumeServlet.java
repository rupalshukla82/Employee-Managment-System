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
@WebServlet(name = "EmpViewResumeServlet", urlPatterns = {"/EmpViewResumeServlet"})
public class EmpViewResumeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("Username") == null){
            response.sendRedirect("login.html");
            return;
        }
        String username = (String) session.getAttribute("Username");
        
        try{
            
            Connection conn = DBConnection.getConnection();
            String q = "select resume from empsignup where username=?";
            
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                byte[] resumebytes = rs.getBytes("resume");
                
                if(resumebytes !=null && resumebytes.length >0){
                    
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "inline; filename=resume.pdf");
                    response.setContentLength(resumebytes.length);
                    response.getOutputStream().write(resumebytes);
                    response.getOutputStream().flush();
                }
                else{
                    response.setContentType("text/html");
                    response.getWriter().println(
                    "<script>" +
                            "alert('no resume found');"
                            +
                            "window.close();"
                            +
                     "</script>"
                    );
                }
            }
            
        }
        catch(Exception e){
            System.out.println("Error is:"+e);
        }
        
        
       
    }


}
