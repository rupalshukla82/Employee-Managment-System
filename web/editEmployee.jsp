<%@ page import="com.finlogic.employee.model.Employee" %>

<%
    Employee emp = (Employee) request.getAttribute("emp");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
    <link rel="stylesheet" href="CSS/EditEmployee.css"/>
</head>
<body>

<form action="UpdateEmployeeServlet" method="post" enctype="multipart/form-data">
    <h1>Update Employee</h1>

    <input type="hidden" name="id" value="${emp.ID}" />  
    <input type="text"     name="full_name" value="${emp.full_name}" required pattern="[a-zA-Z ]+" title="alphabates only" />
    <input type="text"     name="username"  value="${emp.username}"  readonly  />
    <input type="password" name="password"  value="${emp.password}"  readonly  />
    <input type="email"    name="email"     value="${emp.email}"     readonly  />
    <input type="tel"      name="phone"     value="${emp.phone}"     required pattern="[0-9]{10}" title="Enter 10 digit phone number" />
    <input type="text"     name="city"      value="${emp.city}"      required pattern="[a-zA-Z ]+" title="alphabates only" />
    <input type="text"     name="position"  value="${emp.position}"  required pattern="[a-zA-Z ]+" title="alphabates only" />
    <input type="text"  name="department"  value="${emp.department}" required pattern="[a-zA-Z ]+" title="alphabates only" />
    <input type="file"     name="resume"    accept=".pdf" />
    <small>Leave blank if you don't want to change resume</small>

    <input type="submit" value="Update Employee" />
</form>

</body>
</html>