package com.finlogic.employee.servlet;
import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//read from form feild
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String role = request.getParameter("role"); // "admin" or "employee"

        try {
            
            Connection conn = DBConnection.getConnection();
//here DB col name 
            String q = "SELECT * FROM empsignup WHERE username=? AND password=? ";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ps.setString(2, password);
//            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Login success if admin then index page if employee then emp.jsp 
                HttpSession session = request.getSession();
                session.setAttribute("Username", username);
                session.setAttribute("FullName", rs.getString("full_name"));

               response.sendRedirect("EmpDashboardServelet");

//            conn.close();
            }
            else{
                response.setContentType("text/html");
                response.getWriter().println(
                "<script>" +
                        "alert('Sorry no User found:( please Sgnup first');" 
                        +
                          "window.location.href='RegistrationForm.html';" 
                        +
                        "</script>"
                );
            }

        } catch (Exception e) {
            System.out.println("Error is :"+e);
            e.printStackTrace();
            response.sendRedirect("login.html?error=2");
            
        }
    }
    

}