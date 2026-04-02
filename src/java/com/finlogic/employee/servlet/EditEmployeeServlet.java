/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.finlogic.employee.servlet;

import com.finlogic.employee.DAO.EmployeeDao;
import com.finlogic.employee.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhi
 */
@WebServlet( urlPatterns = {"/EditEmployeeServlet"})
public class EditEmployeeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int ID = Integer.parseInt(request.getParameter("id"));

        EmployeeDao dao = new EmployeeDao();
        Employee emp = dao.getEmployeeById(ID);

        request.setAttribute("emp", emp);
        RequestDispatcher rd = request.getRequestDispatcher("editEmployee.jsp");
        rd.forward(request, response);
    }
}
