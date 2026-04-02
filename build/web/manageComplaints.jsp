<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> complaintList =
        (List<Map<String, String>>) request.getAttribute("complaintList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Complaints</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/manageComplaints.css"/>
</head>
<body>

    <div class="navbar">
        <h2>
            <i class="fa-solid fa-triangle-exclamation"></i>
            &nbsp; Manage Complaints
        </h2>
        <a href="AdminDashboardServlet" class="back-btn">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <div class="container">

        <div class="stats-grid">
            <div class="stat-card total">
                <i class="fa-solid fa-list"></i>
                <h3>Total Complaints</h3>
                <p>${totalComplaints}</p>
            </div>
            <div class="stat-card open">
                <i class="fa-solid fa-clock"></i>
                <h3>Open</h3>
                <p>${openComplaints}</p>
            </div>
            <div class="stat-card inprogress">
                <i class="fa-solid fa-spinner"></i>
                <h3>In Progress</h3>
                <p>${progressComplaints}</p>
            </div>
            <div class="stat-card resolved">
                <i class="fa-solid fa-circle-check"></i>
                <h3>Resolved</h3>
                <p>${resolvedComplaints}</p>
            </div>
        </div>

        <div class="table-wrapper">
            <table>
                <tr>
                    <th>#</th>
                    <th>Employee</th>
                    <th>Department</th>
                    <th>Type</th>
                    <th>Subject</th>
                    <th>Description</th>
                    <th>Filed On</th>
                    <th>Status</th>
                    <th>Update Status</th>
                </tr>

                <% if(complaintList == null || complaintList.isEmpty()){ %>
                <tr>
                    <td colspan="9">
                        <div class="no-data">
                            <i class="fa-solid fa-triangle-exclamation"></i>
                            No complaints found!
                        </div>
                    </td>
                </tr>
                <% } else {
                    int count = 1;
                    for(Map<String,String> c : complaintList){ %>
                <tr>
                    <td><%= count++ %></td>
                    <td><b><%= c.get("emp_name") %></b></td>
                    <td><%= c.get("department") %></td>
                    <td><%= c.get("type") %></td>
                    <td><%= c.get("subject") %></td>
                    <td><%= c.get("description") %></td>
                    <td><%= c.get("filed_at") %></td>
                    <td>
                        <span class="badge <%= c.get("status") %>">
                            <%= c.get("status") %>
                        </span>
                    </td>
                    <td>
                        <% if(!c.get("status").equals("CLOSED")){ %>
                        <form action="UpdateComplaintStatusServlet"
                              method="POST" class="status-form">
                            <input type="hidden" name="complaint_id"
                                   value="<%= c.get("id") %>" />
                            <select name="status">
                                <option value="OPEN">Open</option>
                                <option value="INPROGRESS">In Progress</option>
                                <option value="RESOLVED">Resolved</option>
                                <option value="CLOSED">Closed</option>
                            </select>
                            <button type="submit">
                                <i class="fa-solid fa-check"></i>
                            </button>
                        </form>
                        <% } else { %>
                            <span style="color:#888; font-size:0.82rem;">Closed</span>
                        <% } %>
                    </td>
                </tr>
                <% } } %>
            </table>
        </div>
    </div>
</body>
</html>
