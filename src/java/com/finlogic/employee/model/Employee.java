/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.employee.model;

import java.io.InputStream;

/**
 *
 * @author abhi
 */
public class Employee {
   private int ID; 
   private String full_name;
   private String username;
    private String password;
   private String email;
   private String phone;
    private String city;
    private String position;
    private String department;
    private byte[] resume;
    
    public Employee(){
        
    }

   
 public Employee(String full_name, String username, String password ,String email, String phone, String city, String position, String department, byte[] resume) {
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.position = position;
        this.department = department;
        this.resume = resume;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
     public Employee(int ID, String full_name, String username, String password, String email, String phone, String city, String position, String department, byte[] resume) {
        this.ID = ID;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.position = position;
        this.department = department;
        this.resume = resume;
    }

   
    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getPosition() {
        return position;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "ID=" + ID + ", full_name=" + full_name + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", city=" + city + ", position=" + position + ", department=" + department + ", resume=" + resume + '}';
    }

 
    
    
    
}
