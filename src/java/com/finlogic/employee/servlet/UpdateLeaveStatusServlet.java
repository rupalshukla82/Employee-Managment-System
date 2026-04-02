package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UpdateLeaveStatusServlet", urlPatterns = {"/UpdateLeaveStatusServlet"})
public class UpdateLeaveStatusServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("AdminLogin.html");
            return;
        }

        String leaveId = request.getParameter("leave_id");
        String status  = request.getParameter("status");

        try {
            Connection conn = DBConnection.getConnection();
            String q = "UPDATE leaves SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(leaveId));

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                response.setContentType("text/html");
                response.getWriter().println(
                    "<script>" +
                    "alert('Leave " + status + " successfully!');" +  // ✅ Fixed
                    "window.location.href='ManageLeavesServlet';" +
                    "</script>"
                );
            } else {
                response.setContentType("text/html");
                response.getWriter().println(
                    "<script>" +
                    "alert('Update failed! Try again.');" +
                    "window.history.back();" +
                    "</script>"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}