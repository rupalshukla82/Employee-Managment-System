<%@ page import="java.util.*, com.finlogic.employee.DAO.EmployeeDao, com.finlogic.employee.model.Employee" %>
<%
    String Searchtxt = request.getParameter("search");
    EmployeeDao dao = new EmployeeDao();
    List<Employee> list; 

    if(Searchtxt !=null && !Searchtxt.trim().isEmpty()){
        list = dao.getEmployeeByDept(Searchtxt);
    }
    else{
        list=dao.getAllEmployee();
        Searchtxt="";
    }
    
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Employees</title>
    <link rel="stylesheet" href="CSS/ViewEmployee.css"/>
</head>
<body>

    <h1>Employees List</h1>
    
    <a href="AdminDashboardServlet" class="back-btn">&#8592; Back to Dashboard</a>
    <!-- Add this at the top of your page -->
    <br><br>
    
    <form action="ViewEmployees.jsp" method="get">
        
        <input type="text" name="search" placeholder="Search by name Dept and city">
        <input type="submit" value="Search" class="back-btn">
        <%  if(!Searchtxt.isEmpty()){ %>
        <a href="ViewEmployees.jsp" class="back-btn">Clear</a>
          
        <% } %>
    </form>
    <br>
    <p style="text-align:center; color:#888;">
        Showing <%= list.size() %> employee(s)
        <% if(!Searchtxt.isEmpty()){ %>
            for "<b><%= Searchtxt %></b>"
        <% } %>
    </p>
    <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Phone</th>
            <th>City</th>
            <th>Position</th>
            <th>department</th>
            <th>Update</th>
            <th>Delete</th>
            <th>View Resume</th>
        </tr>
        <%
            for(Employee emp : list){
        %>
        <tr>
            <td><%= emp.getID() %></td>
            <td><%= emp.getFull_name() %></td>
            <td><%= emp.getUsername() %></td>
            <td><%= emp.getEmail() %></td>
            <td><%= emp.getPhone() %></td>
            <td><%= emp.getCity() %></td>
            <td><%= emp.getPosition() %></td>
            <td><%= emp.getDepartment()%></td>
            <td>
                <a href="EditEmployeeServlet?id=<%= emp.getID() %>">Edit</a>
            </td>
            <td> 
                <a href="DeleteEmployeeServlet?id=<%= emp.getID() %>" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
            
            <td>
                <a href="ViewResumeServlet?id=<%= emp.getID() %>">View Resume</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>