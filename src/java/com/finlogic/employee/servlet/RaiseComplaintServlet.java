package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RaiseComplaintServlet", urlPatterns = {"/RaiseComplaintServlet"})
public class RaiseComplaintServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ✅ Capital U
        if (session == null || session.getAttribute("Username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username    = (String) session.getAttribute("Username");
        String type        = request.getParameter("type");
        String subject     = request.getParameter("subject");
        String description = request.getParameter("description");

        System.out.println("Raising complaint for: " + username); // debug

        try {
            Connection conn = DBConnection.getConnection();

            String empQ = "SELECT id, full_name, department FROM empsignup WHERE username=?";
            PreparedStatement empPs = conn.prepareStatement(empQ);
            empPs.setString(1, username);
            ResultSet empRs = empPs.executeQuery();

            if (empRs.next()) {
                int empId      = empRs.getInt("id");
                String empName = empRs.getString("full_name");
                String dept    = empRs.getString("department");

                System.out.println("Inserting complaint for empId: " + empId); // debug

                String q = "INSERT INTO complaints(emp_id, emp_name, department, type, subject, description) " +
                           "VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(q);
                ps.setInt(1, empId);
                ps.setString(2, empName);
                ps.setString(3, dept);
                ps.setString(4, type);
                ps.setString(5, subject);
                ps.setString(6, description);

                boolean status = ps.executeUpdate() > 0;
                System.out.println("Insert status: " + status); // debug

                if (status) {
                    response.setContentType("text/html");
                    response.getWriter().println(
                        "<script>" +
                        "alert('Complaint submitted successfully!');" +
                        "window.location.href='ViewMyComplaintsServlet';" +
                        "</script>"
                    );
                } else {
                    response.setContentType("text/html");
                    response.getWriter().println(
                        "<script>" +
                        "alert('Something went wrong! Try again.');" +
                        "window.history.back();" +
                        "</script>"
                    );
                }
            } else {
                System.out.println("Employee not found!"); // debug
                response.sendRedirect("login.html");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("RaiseComplaint.html");
    }
}