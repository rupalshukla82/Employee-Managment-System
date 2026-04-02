package com.finlogic.employee.DAO;

import com.finlogic.employee.config.DBConnection;
import com.finlogic.employee.model.Employee;
import java.sql.*;
import java.util.*;

public class EmployeeDao {

    // Add Employee (already existing)
    public boolean addEmployee(Employee emp) {
        String q = "INSERT INTO empsignup(full_name, username,password, email, phone, city, position, department, resume) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, emp.getFull_name());
            ps.setString(2, emp.getUsername());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getCity());
            ps.setString(7, emp.getPosition());
            ps.setString(8, emp.getDepartment());
            ps.setBytes(9, emp.getResume());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // View all employees (already existing)
// View all employees
public List<Employee> getAllEmployee() {
    List<Employee> list = new ArrayList<>();
    String q = "SELECT * FROM empsignup";

    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(q);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("city"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getBytes("resume")
            );

            list.add(emp);
        }

    } catch (Exception e) {
        System.out.println("Error: " + e);
    }

    return list;
}

    // Delete employee by ID
    public boolean deleteEmployee(int id) {
        String q = "DELETE FROM empsignup WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // Get employee by ID
   public Employee getEmployeeById(int id) {
    String q = "SELECT * FROM empsignup WHERE id=?";

    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return new Employee(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("city"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getBytes("resume")
            );
        }

    } catch (Exception e) {
        System.out.println("Error: " + e);
    }

    return null;
}

    // Update employee

   
      public boolean updateEmployee(Employee emp) {

        try (Connection conn = DBConnection.getConnection()) {

           

            if (emp.getResume() != null) {

                String q = "UPDATE empsignup SET full_name=?, username=?, password=?, email=?, phone=?, city=?, position=?, department=?, resume=? WHERE id=?";

                PreparedStatement ps = conn.prepareStatement(q);
                ps.setString(1, emp.getFull_name());
                ps.setString(2, emp.getUsername());
                ps.setString(3, emp.getPassword());
                ps.setString(4, emp.getEmail());
                ps.setString(5, emp.getPhone());
                ps.setString(6, emp.getCity());
                ps.setString(7, emp.getPosition());
                ps.setString(8, emp.getDepartment());
                ps.setBytes(9, emp.getResume());
                ps.setInt(10, emp.getID());
                
                return ps.executeUpdate()>0;

            } else {

                String q = "UPDATE empsignup SET full_name=?, username=?,password=?, email=?, phone=?, city=?, position=?, department=? WHERE id=?";

               PreparedStatement ps1 = conn.prepareStatement(q);
                ps1.setString(1, emp.getFull_name());
                ps1.setString(2, emp.getUsername());
                ps1.setString(3, emp.getPassword());
                ps1.setString(4, emp.getEmail());
                ps1.setString(5, emp.getPhone());
                ps1.setString(6, emp.getCity());
                ps1.setString(7, emp.getPosition());
                ps1.setString(8, emp.getDepartment());
                ps1.setInt(9, emp.getID());
                 return ps1.executeUpdate() > 0;
                 
            }

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
      
     public List<Employee> getEmployeeByDept(String Searchtxt){
         List <Employee> list = new ArrayList<>();
         
         String q = "select * from empsignup where department like ? or full_name like ? or city like ?";
         
         try{
             Connection conn= DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q);
             String keyword = "%" + Searchtxt + "%";
             ps.setString(1, keyword);
             ps.setString(2, keyword);
             ps.setString(3, keyword);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                  list.add (new Employee(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("city"),
                    rs.getString("position"),
                    rs.getString("department"),      
                    rs.getBytes("resume")
            ));
             }
             
         }
         catch(Exception e){
                     System.out.println("error is:"+e);
//                     e.printStackTrace();
         }
         
         return list;
     } 
}