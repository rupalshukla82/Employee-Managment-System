package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ManageComplaintsServlet", urlPatterns = {"/ManageComplaintsServlet"})
public class ManageComplaintsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ✅ Admin uses small u — check your AdminLoginServlet
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("AdminLogin.html");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            String q = "SELECT * FROM complaints ORDER BY filed_at DESC";
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            List<Map<String, String>> complaintList = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> c = new HashMap<>();
                c.put("id",          rs.getString("id"));
                c.put("emp_name",    rs.getString("emp_name"));
                c.put("department",  rs.getString("department"));
                c.put("type",        rs.getString("type"));
                c.put("subject",     rs.getString("subject"));
                c.put("description", rs.getString("description"));
                c.put("status",      rs.getString("status"));
                c.put("filed_at",    rs.getString("filed_at"));
                complaintList.add(c);
            }

            PreparedStatement ps1 = conn.prepareStatement("SELECT COUNT(*) FROM complaints");
            PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*) FROM complaints WHERE status='OPEN'");
            PreparedStatement ps3 = conn.prepareStatement("SELECT COUNT(*) FROM complaints WHERE status='INPROGRESS'");
            PreparedStatement ps4 = conn.prepareStatement("SELECT COUNT(*) FROM complaints WHERE status='RESOLVED'");

            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();
            ResultSet rs4 = ps4.executeQuery();

            if(rs1.next()) request.setAttribute("totalComplaints",    rs1.getInt(1));
            if(rs2.next()) request.setAttribute("openComplaints",     rs2.getInt(1));
            if(rs3.next()) request.setAttribute("progressComplaints", rs3.getInt(1));
            if(rs4.next()) request.setAttribute("resolvedComplaints", rs4.getInt(1));

            request.setAttribute("complaintList", complaintList);
            request.getRequestDispatcher("manageComplaints.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}