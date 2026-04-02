<%-- 
    Document   : EmpEditProfile
    Created on : Mar 22, 2026, 11:45:54 AM
    Author     : abhi
--%>

<%@page import="com.finlogic.employee.model.Employee" %>
<%
    Employee emp = (Employee) request.getAttribute("emp");
//    
//    if(emp==null){
//        response.sendRedirect("EmpDashboardServlet");
//        return;
//    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit profile</title>
        <link rel="stylesheet" href="CSS/EmpEditProfile.css"/>
    </head>
    <body>
        <h1>Edit Profile</h1>
        <form action="EmpEditPtofileServlet" method="post" enctype="multipart/form-data">
            
            <input type="hidden" name="id" value="<%=emp.getID()%>" />
            <input type="text" name="username" value="<%= emp.getUsername() %>" readonly />
            <input type="text" name="full_name" value="<%= emp.getFull_name() %>" required pattern="[a-zA-Z ]+" title="alphabates only" />
            <input type="text" name="email" value="<%= emp.getEmail() %>" readonly />
            <input type="tel" name="phone" value="<%= emp.getPhone() %>" required pattern="[0-9]{10}" title="Enter 10 digit phone number" />
            <input type="text" name="city" value="<%= emp.getCity() %>" required pattern="[a-zA-Z ]+" title="alphabates only" />
            <input type="text" name="position" value="<%=emp.getPosition()%>" readonly />
            <input type="text" name="department" value="<%= emp.getDepartment()%>" readonly />
            <label>Update Resume:</label>
            <input type="file" name="resume" accept=".pdf" />
            <input type="submit" value="Update Profile">
            <a href="EmpDashboardServelet" class="back" >← Cancel</a>
        </form>
    </body>
</html>
