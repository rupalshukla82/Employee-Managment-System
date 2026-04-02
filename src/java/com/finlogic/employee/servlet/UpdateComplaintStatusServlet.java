package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UpdateComplaintStatusServlet", urlPatterns = {"/UpdateComplaintStatusServlet"})
public class UpdateComplaintStatusServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ✅ Admin uses small u
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("AdminLogin.html");
            return;
        }

        String complaintId = request.getParameter("complaint_id");
        String status      = request.getParameter("status");

        try {
            Connection conn = DBConnection.getConnection();
            String q = "UPDATE complaints SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(complaintId));

            boolean result = ps.executeUpdate() > 0;

            if (result) {
                response.setContentType("text/html");
                response.getWriter().println(
                    "<script>" +
                    "alert('Status updated to " + status + "!');" +
                    "window.location.href='ManageComplaintsServlet';" +
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