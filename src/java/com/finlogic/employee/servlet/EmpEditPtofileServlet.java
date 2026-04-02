/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;


import com.finlogic.employee.config.DBConnection;
import com.finlogic.employee.model.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author abhi
 */
@WebServlet(name = "EmpEditPtofileServlet", urlPatterns = {"/EmpEditPtofileServlet"})
@MultipartConfig(maxFileSize = 2097152)
public class EmpEditPtofileServlet extends HttpServlet {
   //setting employee object fro getting data in the EditEmpProfile.jsp  
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("Username") ==null ){
            response.sendRedirect("login.html");
            return;
        }
        
        String username = (String) session.getAttribute("Username");
        
        try{
            Connection conn = DBConnection.getConnection();
            String q = "select * from empsignup where username=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Employee emp = new Employee (
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("city"),
                        rs.getString("position"),
                        rs.getString("department"),
                        rs.getBytes("resume")
        
                );
                request.setAttribute("emp", emp);
                request.getRequestDispatcher("EmpEditProfile.jsp").forward(request, response);
                
            }
            else{
                response.sendRedirect("EmpDashboardServlet");
            }
        }catch(Exception e){
            System.out.println("Error is:"+e);
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //checking session
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("Username")==null){
            response.sendRedirect("login.html");
            return;
        }
        
        //read from fileds 
        
        int ID = Integer.parseInt(request.getParameter("id")); //hidden
        String FullName = request.getParameter("full_name"); //take
        String Phone = request.getParameter("phone"); //take
        String City = request.getParameter("city"); //take       
        Part ResumePart = request.getPart("resume"); //optional
        byte[] resume = null;
        if(ResumePart!=null && ResumePart.getSize()>0){
            resume = ResumePart.getInputStream().readAllBytes();
        }
        
        try{
            Connection conn = DBConnection.getConnection();
            
             PreparedStatement ps;
//            withb resume
            if(resume!=null){
                    String q = "update empsignup set full_name=? , phone=? ,city=? , resume=? where id=?";

                    ps= conn.prepareStatement(q);
                    ps.setString(1,FullName);
                    ps.setString(2,Phone);
                    ps.setString(3, City);
                    ps.setBytes(4, resume);
                    ps.setInt(5, ID);
            }
            //without resume
            else{
                   String q = "update empsignup set full_name=? , phone=?, city=? where id=?";
            
                    ps= conn.prepareStatement(q);
                    ps.setString(1,FullName);
                    ps.setString(2,Phone);
                    ps.setString(3, City);
                    ps.setInt(4, ID);
                    
            }
            
              boolean res = ps.executeUpdate() >0;
                    
                    if(res){
                        response.setContentType("text/html");
                        response.getWriter().println(
                                "<script>"+
                         "alert('profile Updated sucessfully:)');"
                                +
                          "window.location.href='EmpDashboardServelet';"
                                +
                                 "</script>"
                        );
                    }
                    else{
                        
                        response.setContentType("text/html");
                        response.getWriter().println(
                                "<script>"+
                         "alert('Update fail! Try again:)');"
                                +
                          "window.history.back();"
                                +
                                 "</script>"
                        );
                        
                    } 
            
        }
        catch(Exception e){
            System.out.println("Error:"+e);
        }
      
        
    }


}
