/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.interfaces;

import com.mycompany.managerstudent.models.Student;
import java.util.List;

/**
 *
 * @author anhlnt
 */
public interface IStudent {
    public List<Student> getAll() throws Exception;
    
    public int countStudent() throws Exception;
    
    public List<Student> getAllByPage(int start,int total) throws Exception;

    public void delete(int id) throws Exception;

    public void update(Student student)throws Exception;

    public void add(Student student) throws Exception;
    
    public void readFileExcel();

    public Student getById(String student_code) throws Exception;
    
    public List<String> getAllId() throws Exception;
    
    public List<Student> getByStudentClass(String student_class) throws Exception;
    
    public List<Student> getByFirstName(String first_name) throws Exception;
    
    public List<Student> getByPhone(String phone) throws Exception;
    
    public List<Student> getByPresenter(String presenter) throws Exception;
    
    public List<Student> getByArea(String area) throws Exception;
}
