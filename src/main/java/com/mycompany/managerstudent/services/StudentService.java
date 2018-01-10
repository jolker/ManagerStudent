package com.mycompany.managerstudent.services;

import com.mycompany.managerstudent.DB.StudentDB;
import com.mycompany.managerstudent.interfaces.IStudent;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.util.SlackUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dientt
 */
public class StudentService implements IStudent {

    private static final StudentService INSTANCE = new StudentService();
    private static final String TAG = StudentService.class.getName();

    private StudentService() {

    }

    public static StudentService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Student> getAll() throws Exception {

        return StudentDB.getInstance().getAll();
    }
    
    @Override
    public int countStudent() throws Exception {

        return StudentDB.getInstance().countStudent();
    }

    @Override
    public List<Student> getAllByPage(int start, int total) throws Exception {
        return StudentDB.getInstance().getAllByPage(start, total);
    }

    @Override
    public void delete(int id) throws Exception {
        StudentDB.getInstance().delete(id);
    }

    @Override
    public void readFileExcel() {
        try {
            StudentDB.getInstance().readFileExcel();
        } catch (Exception ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Student student) throws Exception {
        StudentDB.getInstance().update(student);
    }

    @Override
    public void add(Student student) throws Exception {
        StudentDB.getInstance().add(student);
    }

    @Override
    public Student getById(String student_code) throws Exception {
        return StudentDB.getInstance().getById(student_code);
    }

    public List<Student> getGamesByStrId(String idStr) {
        List<Student> students = new ArrayList<>();
        if (idStr != null) {
            String[] arrId = idStr.split(",");
            for (String str : arrId) {
                try {
                    Student student = StudentService.getInstance().getById(str);
                    if (student != null) {
                        students.add(student);
                    }
                } catch (Exception ex) {
                    SlackUtils.printDebug(TAG, ex);
                }
            }
        }
        return students;
    }

    public Map<String, Student> convertToMap(List<Student> students) {
        Map<String, Student> retval = new HashMap<>();
        for (Student student : students) {
            retval.put(student.getStudent_code(), student);
        }
        return retval;
    }

    public Map<String, Student> getMapGame() throws Exception {
        return convertToMap(getAll());
    }

    @Override
    public List<String> getAllId() throws Exception {
        return StudentDB.getInstance().getAllId();
    }

    @Override
    public List<Student> getByStudentClass(String student_class) throws Exception {
        return StudentDB.getInstance().getByStudentClass(student_class);
    }

    @Override
    public List<Student> getByFirstName(String first_name) throws Exception {
        return StudentDB.getInstance().getByFirstName(first_name);
    }

    @Override
    public List<Student> getByPhone(String phone) throws Exception {
        return StudentDB.getInstance().getByPhone(phone);
    }

    @Override
    public List<Student> getByPresenter(String presenter) throws Exception {
        return StudentDB.getInstance().getByPresenter(presenter);
    }

    @Override
    public List<Student> getByArea(String area) throws Exception {
        return StudentDB.getInstance().getByArea(area);
    }
}
