package com.finlogic.employee.servlet;

import com.finlogic.employee.DAO.EmployeeDao;
import com.finlogic.employee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/UpdateEmployeeServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UpdateEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

        int ID = Integer.parseInt(request.getParameter("id"));
        String full_name = request.getParameter("full_name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String position = request.getParameter("position");
        String department = request.getParameter("department");

        Part resumePart = request.getPart("resume");
        byte[] resume = null;
             if(resumePart !=null && resumePart.getSize() >0)
             {
                 resume = resumePart.getInputStream().readAllBytes();
             } 
             
        Employee emp = new Employee(ID, full_name, username, password, email, phone, city, position,department, resume);

        EmployeeDao dao = new EmployeeDao();
        boolean status = dao.updateEmployee(emp);

        if (status) {
            response.setContentType("text/html");
            response.getWriter().println(
            "<script>" +
                    "alert('Employeee Updated sucesfully:');" 
                    +
                    "window.location.href='ViewEmployees.jsp';"
                    +
             "</script>"
            );
        }
        else {
             response.setContentType("text/html");
            response.getWriter().println(
            "<script>" +
                    "alert('Employeee Updated fail:');" 
                    +
                    "window.history.back();"
                    +
             "</script>"
            );
        }
    }
}