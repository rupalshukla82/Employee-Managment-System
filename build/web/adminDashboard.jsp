<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
      
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
        <link rel="stylesheet" href="CSS/AdminHome.css"/>
    </head>
    <body>

        <!-- Navbar -->
        <div class="navbar">
            <h2>
                <i class="fa-solid fa-building"></i>
                HR Admin Panel
            </h2>
            <a href="LogOutServlet">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </div>

        <div class="container">

            <p class="welcome">
                <i class="fa-solid fa-hand-wave"></i>
                Welcome, Admin!
            </p>

            <!-- Stats Cards -->
            <div class="stats-grid">

                <div class="stat-card">
                    <div class="icon">
                        <i class="fa-solid fa-users"></i>
                    </div>
                    <h3>Total Employees</h3>
                    <p>${totalEmp}</p>
                </div>

                <div class="stat-card">
                    <div class="icon">
                        <i class="fa-solid fa-file-pdf"></i>
                    </div>
                    <h3>Resumes Uploaded</h3>
                    <p>${totalResume}</p>
                </div>

                <div class="stat-card">
                    <div class="icon">
                        <i class="fa-solid fa-briefcase"></i>
                    </div>
                    <h3>Positions</h3>
                    <p>${totalPosition}</p>
                </div>

                <div class="stat-card">
                    <div class="icon">
                        <i class="fa-solid fa-user-plus"></i>
                    </div>
                    <h3>Latest Joined</h3>
                    <p style="font-size:1rem;">${latestEmp}</p>
                    <small>${latestDate}</small>
                </div>

            </div>

            <!-- Action Cards -->
            <p class="section-title">
                <i class="fa-solid fa-bolt"></i>
                Quick Actions
            </p>

            <div class="action-grid">

                <a href="AddEmployee.html" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-user-plus"></i>
                    </div>
                    <h3>Add Employee</h3>
                    <p>Register a new employee into the system</p>
                </a>

                <a href="ManageLeavesServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-calendar-check"></i>
                    </div>
                    <h3>Manage Leaves</h3>
                    <p>Approve or reject employee leave requests</p>
                </a>

                <a href="ManageComplaintsServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-triangle-exclamation"></i>
                    </div>
                    <h3>Manage Complaints</h3>
                    <p>View and resolve employee complaints</p>
                </a>

                <a href="ViewEmployees.jsp" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-table-list"></i>
                    </div>
                    <h3>View Employees</h3>
                    <p>View, edit, delete and manage all employees</p>
                </a>

            </div>

        </div>

    </body>
</html>