
package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "EmpDashboardServelet", urlPatterns = {"/EmpDashboardServelet"})
public class EmpDashboardServelet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("Username");

        try {
            Connection conn = DBConnection.getConnection();
            String q = "SELECT * FROM empsignup WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Set all data as attributes
                request.setAttribute("full_name", rs.getString("full_name"));
                request.setAttribute("username",  rs.getString("username"));
                request.setAttribute("email",     rs.getString("email"));
                request.setAttribute("phone",     rs.getString("phone"));
                request.setAttribute("city",      rs.getString("city"));
                request.setAttribute("position",  rs.getString("position"));
                request.setAttribute("department", rs.getString("department"));
                request.setAttribute("created_at",rs.getString("created_at"));

                //  Forward to JSP
                request.getRequestDispatcher("empDashboard.jsp")
                       .forward(request, response);
            } else {
                response.sendRedirect("login.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}