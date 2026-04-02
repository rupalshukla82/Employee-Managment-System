package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ManageLeavesServlet")
public class ManageLeavesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check admin session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("AdminLogin.html");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // 2. Fetch all leave applications
            String q = "SELECT * FROM leaves ORDER BY applied_at DESC";
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            List<Map<String, String>> leaveList = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> leave = new HashMap<>();
                leave.put("id",         rs.getString("id"));
                leave.put("emp_name",   rs.getString("emp_name"));
                leave.put("leave_type", rs.getString("leave_type"));
                leave.put("from_date",  rs.getString("from_date"));
                leave.put("to_date",    rs.getString("to_date"));
                leave.put("reason",     rs.getString("reason"));
                leave.put("status",     rs.getString("status"));
                leave.put("applied_at", rs.getString("applied_at"));
                leaveList.add(leave);
            }

            // 3. Count stats
            String totalQ    = "SELECT COUNT(*) FROM leaves";
            String pendingQ  = "SELECT COUNT(*) FROM leaves WHERE status='PENDING'";
            String approvedQ = "SELECT COUNT(*) FROM leaves WHERE status='APPROVED'";
            String rejectedQ = "SELECT COUNT(*) FROM leaves WHERE status='REJECTED'";

            PreparedStatement ps1 = conn.prepareStatement(totalQ);
            PreparedStatement ps2 = conn.prepareStatement(pendingQ);
            PreparedStatement ps3 = conn.prepareStatement(approvedQ);
            PreparedStatement ps4 = conn.prepareStatement(rejectedQ);

            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();
            ResultSet rs4 = ps4.executeQuery();

            if(rs1.next()) request.setAttribute("totalLeaves",    rs1.getInt(1));
            if(rs2.next()) request.setAttribute("pendingLeaves",  rs2.getInt(1));
            if(rs3.next()) request.setAttribute("approvedLeaves", rs3.getInt(1));
            if(rs4.next()) request.setAttribute("rejectedLeaves", rs4.getInt(1));

            request.setAttribute("leaveList", leaveList);
            request.getRequestDispatcher("manageLeaves.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}