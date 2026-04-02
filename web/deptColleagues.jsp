<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> colleagueList =
        (List<Map<String, String>>) request.getAttribute("colleagueList");
    String department = (String) request.getAttribute("department");
    String empName    = (String) request.getAttribute("empName");
    String position   = (String) request.getAttribute("position");
    int totalInDept   = (int) request.getAttribute("totalInDept");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Department</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/deptColleagues.css"/>
   
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <h2>
            <i class="fa-solid fa-users"></i>
            My Department
        </h2>
        <a href="EmpDashboardServelet" class="back-btn">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <div class="container">

        <!-- Department Info -->
        <div class="dept-info">
            <div>
                <h3>
                    <i class="fa-solid fa-building"></i>
                    <%= department %> Department
                </h3>
                <p>View all your colleagues in the same department</p>
            </div>
            <div class="count">
                <p><%= totalInDept %></p>
                <small>Total Members</small>
            </div>
        </div>

        <!-- My Profile Card -->
        <div class="my-card">
            <div class="avatar">
                <i class="fa-solid fa-user"></i>
            </div>
            <div>
                <h4><%= empName %></h4>
                <p><i class="fa-solid fa-briefcase"></i> &nbsp;<%= position %></p>
            </div>
            <span class="you-badge">
                <i class="fa-solid fa-star"></i> You
            </span>
        </div>

        <!-- Colleagues Section -->
        <p class="section-title">
            <i class="fa-solid fa-users"></i>
            Your Colleagues (<%= colleagueList == null ? 0 : colleagueList.size() %>)
        </p>

        <% if(colleagueList == null || colleagueList.isEmpty()){ %>
            <div class="no-data">
                <i class="fa-solid fa-users-slash"></i>
                <p>No colleagues found in your department yet!</p>
            </div>

        <% } else { %>
            <div class="colleagues-grid">
                <% for(Map<String,String> col : colleagueList){ %>
                <div class="colleague-card">
                    <div class="avatar">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <h4><%= col.get("full_name") %></h4>
                    <span class="position-badge">
                        <%= col.get("position") %>
                    </span>
                    <div class="info-row">
                        <i class="fa-solid fa-envelope"></i>
                        <%= col.get("email") %>
                    </div>
                    <div class="info-row">
                        <i class="fa-solid fa-phone"></i>
                        <%= col.get("phone") %>
                    </div>
                    <div class="info-row">
                        <i class="fa-solid fa-city"></i>
                        <%= col.get("city") %>
                    </div>
                </div>
                <% } %>
            </div>
        <% } %>

    </div>
</body>
</html>