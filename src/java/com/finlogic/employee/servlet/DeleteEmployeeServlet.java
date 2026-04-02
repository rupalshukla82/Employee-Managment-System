package com.finlogic.employee.servlet;

import com.finlogic.employee.DAO.EmployeeDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDao dao = new EmployeeDao();
        dao.deleteEmployee(id);
        response.sendRedirect("ViewEmployees.jsp");
    }
}