/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.DB;

import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.SlackUtils;
import com.nct.framework.common.LogUtil;
import com.nct.framework.dbconn.ClientManager;
import com.nct.framework.dbconn.ManagerIF;
import com.nct.framework.util.ConvertUtils;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author anhlnt
 */
public class StudentDB {

    private static final String TAG = StudentDB.class.getName();

    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(StudentDB.class);

    private static final StudentDB INSTANCE = new StudentDB();

    private static final String QUERY_GET_ALL = "SELECT * FROM STUDENT";

    private static final String QUERY_GET_ALL_BY_PAGE = "SELECT * FROM STUDENT LIMIT ?, ?";

    private static final String QUERY_GET_COUNT_STUDENT = "SELECT COUNT(*) FROM STUDENT";

    private static final String QUERY_GET_ALL_ID = "SELECT STUDENT_CODE FROM STUDENT";

    private static final String QUERY_GET_BY_ID = "SELECT * FROM STUDENT WHERE STUDENT_CODE=?";

    private static final String QUERY_GET_BY_CLASS = "SELECT * FROM STUDENT WHERE STUDENT_CLASS LIKE CONCAT('%', ? ,'%')";

    private static final String QUERY_GET_BY_FIRST_NAME = "SELECT * FROM STUDENT WHERE FIRST_NAME LIKE CONCAT('%', ? ,'%')";

    private static final String QUERY_GET_BY_PHONE = "SELECT * FROM STUDENT WHERE PHONE LIKE CONCAT('%', ? ,'%')";

    private static final String QUERY_GET_BY_PRESENTER = "SELECT * FROM STUDENT WHERE PRESENTER LIKE CONCAT('%', ? ,'%')";

    private static final String QUERY_GET_BY_AREA = "SELECT * FROM STUDENT WHERE AREA LIKE CONCAT('%', ? ,'%')";

    private static final String QUERY_UPDATE = "UPDATE STUDENT SET STUDENT_CLASS=?, START_COURSE=?, NUMBER_COURSE=?, LAST_NAME=?, FIRST_NAME=?, SEX=?, PHONE=?, BIRTHDAY=?, DAY_BIRTH=?, MONTH_BIRTH=?, COMPANY=?, EMAIL=?, POSITION_CLASS=?, PRESENTER=?, POSITION_PRESENTER=?, DEPARTMENT=?, ADDRESS=?, DESCRIPTION=?, AREA=?, IMAGE_PROFILE=? WHERE STUDENT_CODE=?";

    private static final String QUERY_DELETE = "DELETE FROM STUDENT WHERE ID=?";

    private static final String QUERY_ADD = "INSERT INTO STUDENT (STUDENT_CLASS, STUDENT_CODE, START_COURSE, NUMBER_COURSE, LAST_NAME, FIRST_NAME, SEX, PHONE, BIRTHDAY, DAY_BIRTH, MONTH_BIRTH, COMPANY, EMAIL, POSITION_CLASS, PRESENTER, POSITION_PRESENTER, DEPARTMENT, ADDRESS, DESCRIPTION, AREA, IMAGE_PROFILE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String IMPORT_QUERY_ADD = "INSERT INTO STUDENT (STUDENT_CLASS, STUDENT_CODE, START_COURSE, NUMBER_COURSE, LAST_NAME, FIRST_NAME, SEX, PHONE, BIRTHDAY, DAY_BIRTH, MONTH_BIRTH, COMPANY, EMAIL, POSITION_CLASS, PRESENTER, POSITION_PRESENTER, DEPARTMENT, ADDRESS, DESCRIPTION, AREA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private StudentDB() {

    }

    public static StudentDB getInstance() {
        return INSTANCE;
    }

    private Student getResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        int id = rs.getInt("ID");
        String student_code = ConvertUtils.toString(rs.getString("STUDENT_CODE"), "");
        String student_class = ConvertUtils.toString(rs.getString("STUDENT_CLASS"), "");
        String start_course = ConvertUtils.toString(rs.getString("START_COURSE"), "");
        int number_course = rs.getInt("NUMBER_COURSE");
        String last_name = ConvertUtils.toString(rs.getString("LAST_NAME"), "");
        String first_name = ConvertUtils.toString(rs.getString("FIRST_NAME"), "");
        String sex = ConvertUtils.toString(rs.getString("SEX"), "");
        String phone = ConvertUtils.toString(rs.getString("PHONE"), "");
        String birthday = ConvertUtils.toString(rs.getString("BIRTHDAY"), "");
        int day_birth = rs.getInt("DAY_BIRTH");
        int month_birth = rs.getInt("MONTH_BIRTH");
        String company = ConvertUtils.toString(rs.getString("COMPANY"), "");
        String email = ConvertUtils.toString(rs.getString("EMAIL"), "");
        String position_class = ConvertUtils.toString(rs.getString("POSITION_CLASS"), "");
        String presenter = ConvertUtils.toString(rs.getString("PRESENTER"), "");
        String position_presenter = ConvertUtils.toString(rs.getString("POSITION_PRESENTER"), "");
        String department = ConvertUtils.toString(rs.getString("DEPARTMENT"), "");
        String address = ConvertUtils.toString(rs.getString("ADDRESS"), "");
        String description = ConvertUtils.toString(rs.getString("DESCRIPTION"), "");
        String area = ConvertUtils.toString(rs.getString("AREA"), "");
        String image_profile = ConvertUtils.toString(rs.getString("IMAGE_PROFILE"), "");

        student.setId(id);
        student.setStudent_class(student_class);
        student.setStudent_code(student_code);
        student.setStart_course(start_course);
        student.setNumber_course(number_course);
        student.setLast_name(last_name);
        student.setFirst_name(first_name);
        student.setSex(sex);
        student.setPhone(phone);
        student.setBirthday(birthday);
        student.setDay_birth(day_birth);
        student.setMonth_birth(month_birth);
        student.setCompany(company);
        student.setEmail(email);
        student.setPosition_class(position_class);
        student.setPresenter(presenter);
        student.setPosition_presenter(position_presenter);
        student.setDepartment(department);
        student.setAddress(address);
        student.setDescription(description);
        student.setArea(area);
        student.setImage_profile(image_profile);

        return student;
    }

    public List<Student> getAll() throws Exception {
        List<Student> students = new ArrayList<>();
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();

            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_ALL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }

                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public int countStudent() throws Exception {
        int result = 0;
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();

            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_COUNT_STUDENT, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return result;
    }

    public List<Student> getAllByPage(int start, int total) throws Exception {
        List<Student> students = new ArrayList<>();
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();

            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_ALL_BY_PAGE, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

                stmt.setInt(1, start - 1);
                stmt.setInt(2, total);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public List<String> getAllId() throws Exception {
        List<String> retval = new ArrayList<>();
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_ALL_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        retval.add(ConvertUtils.toString(rs.getString("STUDENT_CODE"), ""));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return retval;
    }

    public Student getById(String student_code) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        Student student = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, student_code);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        student = getResultSet(rs);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return student;
    }

    public List<Student> getByStudentClass(String student_class) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        List<Student> students = new ArrayList<>();
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_CLASS, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, student_class);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public List<Student> getByFirstName(String first_name) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        List<Student> students = new ArrayList<>();
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_FIRST_NAME, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, first_name);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public List<Student> getByPhone(String phone) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        List<Student> students = new ArrayList<>();
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_PHONE, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, phone);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public List<Student> getByPresenter(String presenter) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        List<Student> students = new ArrayList<>();
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_PRESENTER, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, presenter);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public List<Student> getByArea(String area) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        List<Student> students = new ArrayList<>();
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_BY_AREA, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, area);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(getResultSet(rs));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
        return students;
    }

    public void update(Student student) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_UPDATE)) {
                stmt.setString(1, student.getStudent_class());
                stmt.setString(2, student.getStart_course());
                stmt.setInt(3, student.getNumber_course());
                stmt.setString(4, student.getLast_name());
                stmt.setString(5, student.getFirst_name());
                stmt.setString(6, student.getSex());
                stmt.setString(7, student.getPhone());
                stmt.setString(8, student.getBirthday());
                stmt.setInt(9, student.getDay_birth());
                stmt.setInt(10, student.getMonth_birth());
                stmt.setString(11, student.getCompany());
                stmt.setString(12, student.getEmail());
                stmt.setString(13, student.getPosition_class());
                stmt.setString(14, student.getPresenter());
                stmt.setString(15, student.getPosition_presenter());
                stmt.setString(16, student.getDepartment());
                stmt.setString(17, student.getAddress());
                stmt.setString(18, student.getDescription());
                stmt.setString(19, student.getArea());
                stmt.setString(20, student.getImage_profile());
                stmt.setString(21, student.getStudent_code());

                stmt.executeUpdate();
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
    }

    public void add(Student student) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_ADD, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, student.getStudent_class());
                stmt.setString(2, student.getStudent_code());
                stmt.setString(3, student.getStart_course());
                stmt.setInt(4, student.getNumber_course());
                stmt.setString(5, student.getLast_name());
                stmt.setString(6, student.getFirst_name());
                stmt.setString(7, student.getSex());
                stmt.setString(8, student.getPhone());
                stmt.setString(9, student.getBirthday());
                stmt.setInt(10, student.getDay_birth());
                stmt.setInt(11, student.getMonth_birth());
                stmt.setString(12, student.getCompany());
                stmt.setString(13, student.getEmail());
                stmt.setString(14, student.getPosition_class());
                stmt.setString(15, student.getPresenter());
                stmt.setString(16, student.getPosition_presenter());
                stmt.setString(17, student.getDepartment());
                stmt.setString(18, student.getAddress());
                stmt.setString(19, student.getDescription());
                stmt.setString(20, student.getArea());
                stmt.setString(21, student.getImage_profile());

                stmt.executeUpdate();
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
    }

    public void delete(int id) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_DELETE)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
    }

    public void readFileExcel() throws Exception {

        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();
            try (PreparedStatement stmt = cnn.prepareStatement(IMPORT_QUERY_ADD)) {

                try {
                    FileInputStream file = new FileInputStream(new File(Constants.IMPORT_RESOURCE + "temp.xlsx"));

                    //Create Workbook instance holding reference to .xlsx file
                    XSSFWorkbook workbook = new XSSFWorkbook(file);

                    //Get first/desired sheet from the workbook
                    XSSFSheet sheet = workbook.getSheetAt(0);

                    //Iterate through each rows one by one
                    Iterator<Row> rowIterator = sheet.iterator();

                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        if (row.getRowNum() == 0) {
                            continue; //just skip the rows if row number is 0
                        }
                        //For each row, iterate through all the columns
                        Iterator<Cell> cellIterator = row.cellIterator();
                        int index = 1;
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            //Check the cell type and format accordingly
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_BLANK:
                                    System.out.print(" t");
                                    stmt.setString(index, "");
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue() + "t");
                                    stmt.setInt(index, (int) cell.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue() + "t");
                                    stmt.setString(index, cell.getStringCellValue());
                                    break;
                            }
                            index++;
                        }
                        stmt.executeUpdate();
                        System.out.println("");
                    }
                    file.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            SlackUtils.printDebug(TAG, ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }

    }

}
