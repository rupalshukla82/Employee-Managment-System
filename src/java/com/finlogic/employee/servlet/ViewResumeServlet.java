package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ViewResumeServlet", urlPatterns = {"/ViewResumeServlet"})
public class ViewResumeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParams = request.getParameter("id");

        if (idParams == null || idParams.isEmpty()) {
            response.sendRedirect("AdminDashboardServlet");
            return;
        }

        int empID = Integer.parseInt(idParams.trim()); // ✅ trim() for safety

        try {
            Connection conn = DBConnection.getConnection();
            String q = "SELECT resume FROM empsignup WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                byte[] resumeBytes = rs.getBytes("resume");

                if (resumeBytes != null && resumeBytes.length > 0) {
                    
                    response.setContentType("application/pdf");
                    
                    response.setHeader("Content-Disposition", "inline; filename=resume.pdf");
                    response.setContentLength(resumeBytes.length);
                    response.getOutputStream().write(resumeBytes);
                    response.getOutputStream().flush();

                } else {
                    response.setContentType("text/html");
                    response.getWriter().println(
                        "<script>" +
                        "alert('No resume uploaded!');" +
                        "window.close();" +
                        "</script>"
                    );
                }

            } else {
                response.setContentType("text/html");
                response.getWriter().println(
                    "<script>" +
                    "alert('No Employee found!');" +
                    "window.close();" +
                    "</script>"
                );
            }

        } catch (Exception e) {
            System.out.println("Error is: " + e);
            e.printStackTrace();
        }
    }
}