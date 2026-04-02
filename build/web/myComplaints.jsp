<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> complaintList =
        (List<Map<String, String>>) request.getAttribute("complaintList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Complaints</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/myComplaints.css"/>
</head>
<body>

    <div class="navbar">
        <h2>
            <i class="fa-solid fa-triangle-exclamation"></i>
            &nbsp; My Complaints
        </h2>
        <a href="EmpDashboardServelet" class="back-btn">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <div class="container">

        <a href="RaiseComplaint.html" class="raise-btn">
            <i class="fa-solid fa-plus"></i> Raise New Complaint
        </a>

        <div class="table-wrapper">
            <table>
                <tr>
                    <th>#</th>
                    <th>Type</th>
                    <th>Subject</th>
                    <th>Description</th>
                    <th>Filed On</th>
                    <th>Status</th>
                </tr>

                <% if(complaintList == null || complaintList.isEmpty()){ %>
                <tr>
                    <td colspan="6">
                        <div class="no-data">
                            <i class="fa-solid fa-triangle-exclamation"></i>
                            No complaints filed yet!
                        </div>
                    </td>
                </tr>
                <% } else {
                    int count = 1;
                    for(Map<String,String> c : complaintList){ %>
                <tr>
                    <td><%= count++ %></td>
                    <td><%= c.get("type") %></td>
                    <td><%= c.get("subject") %></td>
                    <td><%= c.get("description") %></td>
                    <td><%= c.get("filed_at") %></td>
                    <td>
                        <span class="badge <%= c.get("status") %>">
                            <%= c.get("status") %>
                        </span>
                    </td>
                </tr>
                <% } } %>
            </table>
        </div>
    </div>
</body>
</html>