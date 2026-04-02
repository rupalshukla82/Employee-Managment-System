<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="stylesheet" href="CSS/empDashboard.css"/>
</head>
<body>

    <!-- ── Sidebar ── -->
    <div class="sidebar">

        <!-- Profile in Sidebar -->
        <div class="sidebar-profile">
            <div class="profile-avatar">
                <i class="fa-solid fa-user"></i>
            </div>
            <h3>${full_name}</h3>
            <p>${position}</p>
            <span class="dept-badge">
                <i class="fa-solid fa-building"></i>
                &nbsp;${department}
            </span>
        </div>

        <!-- Menu -->
        <div class="sidebar-menu">
            <p class="menu-label">Main Menu</p>
            <a href="EmpDashboardServelet" class="menu-item active">
                <i class="fa-solid fa-gauge"></i> Dashboard
            </a>
            <a href="EmpEditPtofileServlet" class="menu-item">
                <i class="fa-solid fa-user-pen"></i> Edit Profile
            </a>
            <a href="EmpViewResumeServlet" target="_blank" class="menu-item">
                <i class="fa-solid fa-file-pdf"></i> View Resume
            </a>

            <hr class="menu-divider"/>

            <p class="menu-label">My Activities</p>
            <a href="ViewMyLeavesServlet" class="menu-item">
                <i class="fa-solid fa-calendar-xmark"></i> My Leaves
            </a>
            <a href="ViewMyComplaintsServlet" class="menu-item">
                <i class="fa-solid fa-triangle-exclamation"></i> My Complaints
            </a>
            <a href="ViewDeptColleaguesServlet" class="menu-item">
                <i class="fa-solid fa-users"></i> My Department
            </a>

            <hr class="menu-divider"/>

            <p class="menu-label">Account</p>
            <a href="ResetPassword.html" class="menu-item">
                <i class="fa-solid fa-lock"></i> Change Password
            </a>
        </div>

        <!-- Logout -->
        <div class="sidebar-logout">
            <a href="LogOutServlet">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>
        </div>
    </div>

    <!-- ── Main Content ── -->
    <div class="main-content">

        <!-- Topbar -->
        <div class="topbar">
            <h2>
                <i class="fa-solid fa-gauge"></i>
                Dashboard
            </h2>
            <div class="topbar-right">
                <span>Welcome back, <b>${username}</b>!</span>
            </div>
        </div>

        <div class="container">

            <!-- Welcome Banner -->
            <div class="welcome-banner">
                <div>
                    <h2>Hello, ${full_name}! 👋</h2>
                    <p>Here's your profile overview and quick actions.</p>
                </div>
                <i class="fa-solid fa-id-badge banner-icon"></i>
            </div>

            <!-- Stats Row -->
            <div class="stats-row">
                <div class="stat-card">
                    <div class="stat-icon purple">
                        <i class="fa-solid fa-briefcase"></i>
                    </div>
                    <div>
                        <h4>Position</h4>
                        <p>${position}</p>
                    </div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon blue">
                        <i class="fa-solid fa-building"></i>
                    </div>
                    <div>
                        <h4>Department</h4>
                        <p>${department}</p>
                    </div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon green">
                        <i class="fa-solid fa-city"></i>
                    </div>
                    <div>
                        <h4>City</h4>
                        <p>${city}</p>
                    </div>
                </div>
            </div>

            <!-- Profile Info -->
            <div class="profile-card">
                <div class="card-header">
                    <i class="fa-solid fa-circle-user"></i>
                    <h3>My Profile Information</h3>
                </div>
                <div class="info-grid">
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-user"></i>
                        </div>
                        <div class="info-text">
                            <label>Full Name</label>
                            <span>${full_name}</span>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-at"></i>
                        </div>
                        <div class="info-text">
                            <label>Username</label>
                            <span>${username}</span>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-envelope"></i>
                        </div>
                        <div class="info-text">
                            <label>Email</label>
                            <span>${email}</span>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-phone"></i>
                        </div>
                        <div class="info-text">
                            <label>Phone</label>
                            <span>${phone}</span>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-city"></i>
                        </div>
                        <div class="info-text">
                            <label>City</label>
                            <span>${city}</span>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-icon">
                            <i class="fa-solid fa-calendar"></i>
                        </div>
                        <div class="info-text">
                            <label>Registered On</label>
                            <span>${created_at}</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Quick Actions -->
            <p class="section-title">
                <i class="fa-solid fa-bolt"></i>
                Quick Actions
            </p>

            <div class="action-grid">
                <a href="EmpEditPtofileServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-user-pen"></i>
                    </div>
                    <h3>Edit Profile</h3>
                    <p>Update your personal details</p>
                </a>
                <a href="ViewMyLeavesServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-calendar-xmark"></i>
                    </div>
                    <h3>My Leaves</h3>
                    <p>Apply and track your leave status</p>
                </a>
                <a href="ViewMyComplaintsServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-triangle-exclamation"></i>
                    </div>
                    <h3>My Complaints</h3>
                    <p>Raise and track your complaints</p>
                </a>
                <a href="EmpViewResumeServlet" target="_blank" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-file-pdf"></i>
                    </div>
                    <h3>View Resume</h3>
                    <p>View your uploaded resume</p>
                </a>
                <a href="ViewDeptColleaguesServlet" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-users"></i>
                    </div>
                    <h3>My Department</h3>
                    <p>View colleagues in your department</p>
                </a>
                <a href="ResetPassword.html" class="action-card">
                    <div class="action-icon">
                        <i class="fa-solid fa-lock"></i>
                    </div>
                    <h3>Change Password</h3>
                    <p>Update your login password</p>
                </a>
            </div>

        </div>
    </div>

</body>
</html>
