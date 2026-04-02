<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> leaveList =
        (List<Map<String, String>>) request.getAttribute("leaveList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Leaves</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/manageLeaves.css"/>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <h2>
            <i class="fa-solid fa-calendar-check"></i>
            &nbsp; Manage Leave Applications
        </h2>
        <a href="AdminDashboardServlet" class="back-btn">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <div class="container">

        <!-- Stats Cards -->
        <div class="stats-grid">
            <div class="stat-card total">
                <i class="fa-solid fa-calendar-days"></i>
                <h3>Total Applications</h3>
                <p>${totalLeaves}</p>
            </div>
            <div class="stat-card pending">
                <i class="fa-solid fa-clock"></i>
                <h3>Pending</h3>
                <p>${pendingLeaves}</p>
            </div>
            <div class="stat-card approved">
                <i class="fa-solid fa-circle-check"></i>
                <h3>Approved</h3>
                <p>${approvedLeaves}</p>
            </div>
            <div class="stat-card rejected">
                <i class="fa-solid fa-circle-xmark"></i>
                <h3>Rejected</h3>
                <p>${rejectedLeaves}</p>
            </div>
        </div>

        <!-- Leave Table -->
        <div class="table-wrapper">
            <table>
                <tr>
                    <th>#</th>
                    <th>Employee Name</th>
                    <th>Leave Type</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Reason</th>
                    <th>Applied On</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>

                <% if(leaveList == null || leaveList.isEmpty()){ %>
                <tr>
                    <td colspan="9">
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
                    <td><b><%= leave.get("emp_name") %></b></td>
                    <td><%= leave.get("leave_type") %></td>
                    <td><%= leave.get("from_date") %></td>
                    <td><%= leave.get("to_date") %></td>
                    <td><%= leave.get("reason") %></td>
                    <td><%= leave.get("applied_at") %></td>
                    <td>
                        <span class="badge <%= leave.get("status") %>">
                            <%= leave.get("status") %>
                        </span>
                    </td>
                    <td>
                        <% if(leave.get("status").equals("PENDING")){ %>

                            <!-- Approve Form -->
                            <form action="UpdateLeaveStatusServlet"
                                  method="POST" style="display:inline;">
                                <input type="hidden" name="leave_id"
                                       value="<%= leave.get("id") %>" />
                                <input type="hidden" name="status" value="APPROVED" />
                                <button type="submit" class="btn-approve"
                                        onclick="return confirm('Approve this leave?')">
                                    <i class="fa-solid fa-check"></i> Approve
                                </button>
                            </form>

                            <!-- Reject Form -->
                            <form action="UpdateLeaveStatusServlet"
                                  method="POST" style="display:inline;">
                                <input type="hidden" name="leave_id"
                                       value="<%= leave.get("id") %>" />
                                <input type="hidden" name="status" value="REJECTED" />
                                <button type="submit" class="btn-reject"
                                        onclick="return confirm('Reject this leave?')">
                                    <i class="fa-solid fa-x"></i> Reject
                                </button>
                            </form>

                        <% } else { %>
                            <span style="color:#888; font-size:0.85rem;">
                                Already <%= leave.get("status") %>
                            </span>
                        <% } %>
                    </td>
                </tr>
                <% } } %>
            </table>
        </div>
    </div>
</body>
</html>