<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> leaveList =
        (List<Map<String, String>>) request.getAttribute("leaveList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Leaves</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/myleaves.css"/>
</head>
<body>

    <div class="navbar">
        <h2><i class="fa-solid fa-calendar-xmark"></i> &nbsp; My Leave Applications</h2>
        <a href="EmpDashboardServelet" class="back-btn">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <div class="container">

        <a href="ApplyLeave.html" class="apply-btn">
            <i class="fa-solid fa-plus"></i> Apply New Leave
        </a>

        <div class="table-wrapper">
            <table>
                <tr>
                    <th>#</th>
                    <th>Leave Type</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Reason</th>
                    <th>Applied On</th>
                    <th>Status</th>
                </tr>

                <% if(leaveList == null || leaveList.isEmpty()){ %>
                <tr>
                    <td colspan="7">
                        <div class="no-data">
                            <i class="fa-solid fa-calendar-xmark"></i>
                            No leave applications found!
                        </div>
                    </td>
                </tr>
                <% } else {
                    int count = 1;
                    for(Map<String,String> leave : leaveList){ %>
                <tr>
                    <td><%= count++ %></td>
                    <td><%= leave.get("leave_type") %></td>
                    <td><%= leave.get("from_date") %></td>
                    <td><%= leave.get("to_date") %></td>
                    <td><%= leave.get("reason") %></td>
                    <td><%= leave.get("applied_at") %></td>
                    <td>
                        <!-- ? Color badge based on status -->
                        <span class="badge <%= leave.get("status") %>">
                            <%= leave.get("status") %>
                        </span>
                    </td>
                </tr>
                <% } } %>
            </table>
        </div>
    </div>
</body>
</html>