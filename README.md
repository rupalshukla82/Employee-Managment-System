# 🏢 Employee / HR Management System

---

## 📌 About The Project

This is a complete **HR Management System** built as a personal project to apply and demonstrate Java full-stack development skills learned during industrial training. The system allows an Admin to manage employees and an Employee to manage their own profile, apply for leaves, raise complaints, and view department colleagues — all through a clean, modern web interface.

---

## ✨ Features

### 👨‍💼 Admin Side
- 🔐 Secure Admin Login (manually inserted credentials — no signup)
- ➕ Add new employees with PDF resume upload
- 👁️ View all employees in a searchable table (search by name, position, city)
- ✏️ Edit / Update employee details
- 🗑️ Delete employees
- 📄 View employee resumes directly in browser (PDF streaming)
- 📅 Manage Leave Applications — Approve or Reject with status tracking
- ⚠️ Manage Employee Complaints — Update status (Open → In Progress → Resolved → Closed)
- 📊 Admin Dashboard with stats — Total Employees, Resumes Uploaded, Positions, Latest Joined

### 👤 Employee Side
- 📝 Employee Self-Registration with PDF resume upload
- 🔐 Secure Login with session management
- 🖥️ Modern Employee Dashboard with sidebar layout
- 👤 View own profile (name, email, phone, city, position, department)
- ✏️ Edit own profile (name, phone, city, resume)
- 📄 View own uploaded resume in browser
- 📅 Apply for Leave (Sick, Casual, Emergency, Personal) and track status
- ⚠️ Raise Complaints (Technical, HR, Behaviour, Office issues) and track status
- 👥 View Department Colleagues — see all employees in same department
- 🔒 Reset Password using username + registered email verification
- 🚪 Logout with session invalidation

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Servlets, JSP |
| Frontend | HTML, CSS, Font Awesome Icons |
| Database | MySQL |
| Server | Apache Tomcat 9 |
| IDE | NetBeans |
| Architecture | MVC Pattern + DAO Pattern |

---

## 🗄️ Database Structure

### Tables

```sql
-- Employee registration & login
CREATE TABLE empsignup (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  full_name   VARCHAR(100) NOT NULL,
  username    VARCHAR(50)  NOT NULL UNIQUE,
  password    VARCHAR(20),
  email       VARCHAR(100) NOT NULL UNIQUE,
  phone       VARCHAR(15)  NOT NULL,
  city        VARCHAR(50)  NOT NULL,
  position    VARCHAR(100) NOT NULL,
  department  VARCHAR(50)  NOT NULL,
  resume      LONGBLOB,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admin login (manually inserted)
CREATE TABLE adminlogin (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50)  NOT NULL UNIQUE,
  password VARCHAR(50)  NOT NULL
);

-- Leave applications
CREATE TABLE leaves (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  emp_id     INT NOT NULL,
  emp_name   VARCHAR(100) NOT NULL,
  leave_type VARCHAR(50)  NOT NULL,
  from_date  DATE NOT NULL,
  to_date    DATE NOT NULL,
  reason     TEXT,
  status     VARCHAR(20) DEFAULT 'PENDING',
  applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (emp_id) REFERENCES empsignup(id)
);

-- Complaints
CREATE TABLE complaints (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  emp_id      INT NOT NULL,
  emp_name    VARCHAR(100) NOT NULL,
  department  VARCHAR(50),
  type        VARCHAR(100) NOT NULL,
  subject     VARCHAR(200) NOT NULL,
  description TEXT NOT NULL,
  status      VARCHAR(20) DEFAULT 'OPEN',
  filed_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (emp_id) REFERENCES empsignup(id)
);
```

---

## 🚀 How To Run

### Prerequisites
- JDK 8 or above
- Apache Tomcat 9
- MySQL 8+
- NetBeans IDE (recommended)

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/rupalshukla82/Employee-Managment-System.git
```

**2. Import into NetBeans**
- Open NetBeans → File → Open Project → Select cloned folder

**3. Setup the Database**
- Open MySQL and create a database:
```sql
CREATE DATABASE emplogin;
USE emplogin;
```
- Run all the table creation queries from the **Database Structure** section above.

**4. Insert Admin credentials manually**
```sql
INSERT INTO adminlogin (username, password) VALUES ('admin', 'admin123');
```

**5. Configure DB Connection**
- Open `src/com/finlogic/employee/config/DBConnection.java`
- Update your MySQL username and password:
```java
String url      = "jdbc:mysql://localhost:3306/emplogin";
String username = "root";       // your MySQL username
String password = "yourpassword"; // your MySQL password
```

**6. Add MySQL Connector JAR**
- Download `mysql-connector-java.jar`
- Right click project → Properties → Libraries → Add JAR

**7. Run the project**
- Right click project → Run
- Tomcat will start and browser will open automatically

**8. Access the application**
```
Employee Login  → http://localhost:8081/employee/login.html
Admin Login              → http://localhost:8081/employee/AdminLogin.html
```

---

## 📁 Project Structure

```
Employee-Management-System/
│
├── src/
│   └── com/finlogic/employee/
│       ├── config/
│       │   └── DBConnection.java          → Database connection
│       ├── model/
│       │   └── Employee.java              → Employee model
│       ├── DAO/
│       │   └── EmployeeDao.java           → All DB queries
│       └── servlet/
│           ├── AdminLoginServlet.java
│           ├── AdminDashboardServlet.java
│           ├── LoginServlet.java
│           ├── AddEmpServlet.java
│           ├── EditEmployeeServlet.java
│           ├── UpdateEmployeeServlet.java
│           ├── DeleteEmployeeServlet.java
│           ├── ViewResumeServlet.java
│           ├── EmpDashboardServelet.java
│           ├── EmpEditPtofileServlet.java
│           ├── EmpViewResumeServlet.java
│           ├── ViewDeptColleaguesServlet.java
│           ├── ApplyLeaveServlet.java
│           ├── ViewMyLeavesServlet.java
│           ├── ManageLeavesServlet.java
│           ├── UpdateLeaveStatusServlet.java
│           ├── RaiseComplaintServlet.java
│           ├── ViewMyComplaintsServlet.java
│           ├── ManageComplaintsServlet.java
│           ├── UpdateComplaintStatusServlet.java
│           ├── ResetPasswordServlet.java
│           └── LogOutServlet.java
│
└── web/
    ├── CSS/                               → All stylesheets
    ├── login.html
    ├── AdminLogin.html
    ├── RegistrationForm.html
    ├── AddEmployee.html
    ├── ApplyLeave.html
    ├── RaiseComplaint.html
    ├── ResetPassword.html
    ├── adminDashboard.jsp
    ├── empDashboard.jsp
    ├── ViewEmployees.jsp
    ├── EmpEditProfile.jsp
    ├── myLeaves.jsp
    ├── manageLeaves.jsp
    ├── myComplaints.jsp
    ├── manageComplaints.jsp
    └── deptColleagues.jsp
```

---

## 🔄 Application Flow

```
┌─────────────────────────────────────────────────────┐
│                   Entry Points                       │
│   login.html              AdminLogin.html            │
└──────────┬────────────────────────┬─────────────────┘
           │                        │
           ▼                        ▼
    LoginServlet            AdminLoginServlet
           │                        │
           ▼                        ▼
  EmpDashboardServlet     AdminDashboardServlet
           │                        │
    ┌──────┴──────┐          ┌──────┴──────┐
    │ Edit Profile│          │View Employee│
    │ My Leaves   │          │Add Employee │
    │ Complaints  │          │Manage Leaves│
    │ My Dept     │          │Manage Compl.│
    │ View Resume │          │View Resume  │
    └─────────────┘          └─────────────┘
```


## 👩‍💻 Author

**Rupal Shukla**

-  Live Demo: [Employee-Managemen-System](https://employee-managment-system-production-fcde.up.railway.app/)
-  LinkedIn: [linkedin.com/in/rupal-shukla-932143299](http://linkedin.com/in/rupal-shukla-932143299)
-  GitHub: [github.com/rupalshukla82](https://github.com/rupalshukla82)

---
