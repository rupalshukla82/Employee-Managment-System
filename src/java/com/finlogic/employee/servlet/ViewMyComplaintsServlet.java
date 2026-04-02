package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ViewMyComplaintsServlet", urlPatterns = {"/ViewMyComplaintsServlet"})
public class ViewMyComplaintsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ✅ Capital U — matches your LoginServlet
        if (session == null || session.getAttribute("Username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("Username");
        System.out.println("Username from session: " + username); // debug

        try {
            Connection conn = DBConnection.getConnection();

            // Get emp_id from username
            String empQ = "SELECT id FROM empsignup WHERE username=?";
            PreparedStatement empPs = conn.prepareStatement(empQ);
            empPs.setString(1, username);
            ResultSet empRs = empPs.executeQuery();

            if (empRs.next()) {
                int empId = empRs.getInt("id");
                System.out.println("Employee ID: " + empId); // debug

                String q = "SELECT * FROM complaints WHERE emp_id=? ORDER BY filed_at DESC";
                PreparedStatement ps = conn.prepareStatement(q);
                ps.setInt(1, empId);
                ResultSet rs = ps.executeQuery();

                List<Map<String, String>> complaintList = new ArrayList<>();
                while (rs.next()) {
                    Map<String, String> c = new HashMap<>();
                    c.put("id",          rs.getString("id"));
                    c.put("type",        rs.getString("type"));
                    c.put("subject",     rs.getString("subject"));
                    c.put("description", rs.getString("description"));
                    c.put("status",      rs.getString("status"));
                    c.put("filed_at",    rs.getString("filed_at"));
                    complaintList.add(c);
                }

                System.out.println("Complaint list size: " + complaintList.size()); // debug
                request.setAttribute("complaintList", complaintList);
                request.getRequestDispatcher("myComplaints.jsp").forward(request, response);

            } else {
                System.out.println("Employee not found for username: " + username);
                response.sendRedirect("EmpDashboardServelet");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}