package com.finlogic.employee.servlet;

import com.finlogic.employee.DAO.EmployeeDao;
import com.finlogic.employee.model.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddServlet")
@MultipartConfig(maxFileSize = 5242880)
public class AddEmpServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            Part resumePart = request.getPart("resume");

            //  Step 1 — Check file type
            if (resumePart != null && resumePart.getSize() > 0) {
                String fileName = resumePart.getSubmittedFileName();
                if (fileName == null || !fileName.toLowerCase().endsWith(".pdf")) {
                    response.getWriter().println(
                        "<script>" +
                        "alert('Only PDF files are allowed!');" +
                        "window.history.back();" +
                        "</script>"
                    );
                    return;
                }
            }

            //  Step 2 — Check file size
            if (resumePart != null && resumePart.getSize() > 2 * 1024 * 1024) {
                response.getWriter().println(
                    "<script>" +
                    "alert('File size must be less than 2MB!');" +
                    "window.history.back();" +
                    "</script>"
                );
                return;
            }

            //  Step 3 — Read resume bytes
            byte[] resume = null;
            if (resumePart != null && resumePart.getSize() > 0) {
                resume = resumePart.getInputStream().readAllBytes();
            }

            //  Step 4 — Create Employee object
            Employee emp = new Employee(
                request.getParameter("full_name"),
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("email"),
                request.getParameter("phone"),
                request.getParameter("city"),
                request.getParameter("position"),
                request.getParameter("department"),
                resume
            );

            //  Step 5 — Save to DB
            EmployeeDao dao = new EmployeeDao();
            boolean status = dao.addEmployee(emp);

            String source = request.getParameter("source");

            //  Step 6 — Check if save failed
            if (!status) {
                response.getWriter().println(
                    "<script>" +
                    "alert('Something went wrong! Please try again.');" +
                    "window.history.back();" +
                    "</script>"
                );
                return;
            }

            //  Step 7 — Redirect based on source
            if (source != null && source.equals("admin")) {
                response.getWriter().println(
                    "<script>" +
                    "alert('Employee Added Successfully!');" +
                    "window.location.href='AdminDashboardServlet';" +
                    "</script>"
                );
            } else {
                response.getWriter().println(
                    "<script>" +
                    "alert('Registration Successful! Please Login.');" +
                    "window.location.href='login.html';" +
                    "</script>"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                "<script>" +
                "alert('Something went wrong! Please try again.');" +
                "window.history.back();" +
                "</script>"
            );
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("RegistrationForm.html");
    }
}
