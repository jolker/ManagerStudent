/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.models;

/**
 *
 * @author anhlnt
 */
public class Student {
    
    private int id;
    private String student_class;
    private String student_code;
    private String start_course;
    private int number_course;
    private String last_name;
    private String first_name;
    private String sex;
    private String phone;
    private String birthday;
    private int day_birth;
    private int month_birth;
    private String company;
    private String email;
    private String position_class;
    private String presenter;
    private String position_presenter;
    private String department;
    private String address;
    private String description;
    private String area;
    private String image_profile;

    public Student() {
        
    }

    public Student(int id, String student_class, String student_code, String start_course, int number_course, String last_name, String first_name, String sex, String phone, String birthday, int day_birth, int month_birth, String company, String email, String position_class, String presenter, String position_presenter, String department, String address, String description, String area, String image_profile) {
        this.id = id;
        this.student_class = student_class;
        this.student_code = student_code;
        this.start_course = start_course;
        this.number_course = number_course;
        this.last_name = last_name;
        this.first_name = first_name;
        this.sex = sex;
        this.phone = phone;
        this.birthday = birthday;
        this.day_birth = day_birth;
        this.month_birth = month_birth;
        this.company = company;
        this.email = email;
        this.position_class = position_class;
        this.presenter = presenter;
        this.position_presenter = position_presenter;
        this.department = department;
        this.address = address;
        this.description = description;
        this.area = area;
        this.image_profile = image_profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getStart_course() {
        return start_course;
    }

    public void setStart_course(String start_course) {
        this.start_course = start_course;
    }

    public int getNumber_course() {
        return number_course;
    }

    public void setNumber_course(int number_course) {
        this.number_course = number_course;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getDay_birth() {
        return day_birth;
    }

    public void setDay_birth(int day_birth) {
        this.day_birth = day_birth;
    }

    public int getMonth_birth() {
        return month_birth;
    }

    public void setMonth_birth(int month_birth) {
        this.month_birth = month_birth;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition_class() {
        return position_class;
    }

    public void setPosition_class(String position_class) {
        this.position_class = position_class;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getPosition_presenter() {
        return position_presenter;
    }

    public void setPosition_presenter(String position_presenter) {
        this.position_presenter = position_presenter;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }
    
}
