package com.finlogic.employee.servlet;

import com.finlogic.employee.config.DBConnection;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ViewDeptColleaguesServlet", urlPatterns = {"/ViewDeptColleaguesServlet"})
public class ViewDeptColleaguesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("Username");

        try {
            Connection conn = DBConnection.getConnection();

            // 2. Get logged in employee details
            String empQ = "SELECT id, full_name, department, position FROM empsignup WHERE username=?";
            PreparedStatement empPs = conn.prepareStatement(empQ);
            empPs.setString(1, username);
            ResultSet empRs = empPs.executeQuery();

            if (empRs.next()) {
                int empId          = empRs.getInt("id");
                String empName     = empRs.getString("full_name");
                String department  = empRs.getString("department");
                String position    = empRs.getString("position");

                // 3. Get all colleagues in same department excluding self
                String q = "SELECT id, full_name, position, city, email, phone " +
                           "FROM empsignup " +
                           "WHERE department=? AND id != ? " +
                           "ORDER BY full_name ASC";
                PreparedStatement ps = conn.prepareStatement(q);
                ps.setString(1, department);
                ps.setInt(2, empId);
                ResultSet rs = ps.executeQuery();

                List<Map<String, String>> colleagueList = new ArrayList<>();
                while (rs.next()) {
                    Map<String, String> col = new HashMap<>();
                    col.put("id",        rs.getString("id"));
                    col.put("full_name", rs.getString("full_name"));
                    col.put("position",  rs.getString("position"));
                    col.put("city",      rs.getString("city"));
                    col.put("email",     rs.getString("email"));
                    col.put("phone",     rs.getString("phone"));
                    colleagueList.add(col);
                }

                // 4. Set attributes for JSP
                request.setAttribute("department",    department);
                request.setAttribute("empName",       empName);
                request.setAttribute("position",      position);
                request.setAttribute("colleagueList", colleagueList);
                request.setAttribute("totalInDept",   colleagueList.size() + 1); // +1 includes self

                request.getRequestDispatcher("deptColleagues.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}