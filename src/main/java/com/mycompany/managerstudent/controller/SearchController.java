/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.controller;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.services.StudentService;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.PassingDataUtil;
import com.mycompany.managerstudent.util.SlackUtils;
import com.mycompany.managerstudent.util.Utils;
import com.nct.framework.common.LogUtil;
import com.nct.game.framework.web.view.freemarker.FreeMarker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author anhlnt
 */
public class SearchController extends HttpServlet {

    private static final String DEFAULT_VALUE = "";
    private static final String PASSING_STUDENTS = "students";
    private static final String PASSING_STUDENT = "student";
    private static final String PASSING_SEARCH_CASE = "search_case";
    private static final String PASSING_VALUE = "search_value";
    private static final String PASSING_ACTION = "action";
    private static final String ACTION_SEARCH = "search";
    private static final String ACTION_EXPORT = "export";
    private static final String CASE_STUDENT_CODE = "case_student_codes";
    private static final String CASE_STUDENT_CLASS = "case_student_class";
    private static final String CASE_FIRST_NAME = "case_first_name";
    private static final String CASE_PHONE = "case_phone";
    private static final String CASE_PRESENTER = "case_presenter";
    private static final String CASE_AREA = "case_area";
    private static final String TAG = SearchController.class.getName();

    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(ListStudentController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter(PASSING_ACTION);
        AdminUser userSession = (AdminUser) request.getSession().getAttribute(Constants.USER_SESSION);

        switch (StringUtils.isBlank(action) ? DEFAULT_VALUE : action) {
            case DEFAULT_VALUE:
                initPage(request, response, userSession);

                break;
            case ACTION_SEARCH:
                actionSearch(request, response, userSession);
                break;
            case ACTION_EXPORT:
                actionExport(request, response);
                break;
        }
    }

    private void initPage(HttpServletRequest request, HttpServletResponse response, AdminUser userSession) throws ServletException, IOException {
        try {
            List<Student> students = StudentService.getInstance().getAll();

            Map<String, Object> data = new HashMap<>();
            data.put(Constants.USER_ADMIN, userSession);
            data.putAll(PassingDataUtil.generalPassing());
            data.put(PASSING_STUDENTS, students);

            new FreeMarker(Constants.PAGE_LIST_STUDENT, data).render(request, response);
        } catch (Exception ex) {
            SlackUtils.printDebug(TAG, ex);
            logger.error("error", ex);
        }
    }

    private void actionSearch(HttpServletRequest request, HttpServletResponse response, AdminUser userSession) throws ServletException, IOException {
        try {
            String params_value = request.getParameter(PASSING_VALUE);
            Student student = null;
            List<Student> students = null;

            Map<String, Object> data = new HashMap<>();
            data.put(Constants.USER_ADMIN, userSession);
            data.putAll(PassingDataUtil.generalPassing());

            String search_case = request.getParameter(PASSING_SEARCH_CASE);

            switch (search_case) {
                case CASE_STUDENT_CODE:
                    student = StudentService.getInstance().getById(params_value);

                    data.put(PASSING_STUDENT, student);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
                case CASE_STUDENT_CLASS:
                    students = StudentService.getInstance().getByStudentClass(params_value);

                    data.put(PASSING_STUDENTS, students);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
                case CASE_FIRST_NAME:
                    students = StudentService.getInstance().getByFirstName(params_value);

                    data.put(PASSING_STUDENTS, students);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
                case CASE_PHONE:
                    students = StudentService.getInstance().getByPhone(params_value);

                    data.put(PASSING_STUDENTS, students);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
                case CASE_PRESENTER:
                    students = StudentService.getInstance().getByPresenter(params_value);

                    data.put(PASSING_STUDENTS, students);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
                case CASE_AREA:
                    students = StudentService.getInstance().getByArea(params_value);

                    data.put(PASSING_STUDENTS, students);

                    new FreeMarker(Constants.PAGE_SEARCH_STUDENT, data).render(request, response);
                    break;
            }

        } catch (Exception ex) {
            SlackUtils.printDebug(TAG, ex);
            logger.error("error", ex);
        }
    }

    private void actionExport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String params_value = request.getParameter(PASSING_VALUE);
            Student student = null;
            List<Student> students = null;

            String search_case = request.getParameter(PASSING_SEARCH_CASE);
            switch (search_case) {
                case CASE_STUDENT_CODE:
                    student = StudentService.getInstance().getById(params_value);
                    exportFile(student);

                    break;
                case CASE_STUDENT_CLASS:
                    students = StudentService.getInstance().getByStudentClass(params_value);
                    exportFileList(students);

                    break;
                case CASE_FIRST_NAME:
                    students = StudentService.getInstance().getByFirstName(params_value);
                    exportFileList(students);

                    break;
                case CASE_PHONE:
                    students = StudentService.getInstance().getByPhone(params_value);
                    exportFileList(students);

                    break;
                case CASE_PRESENTER:
                    students = StudentService.getInstance().getByPresenter(params_value);
                    exportFileList(students);

                    break;
                case CASE_AREA:
                    students = StudentService.getInstance().getByArea(params_value);
                    exportFileList(students);

                    break;
            }

        } catch (Exception ex) {
            SlackUtils.printDebug(TAG, ex);
            logger.error("error", ex);
        }
    }

    private void exportFile(Student student) throws FileNotFoundException, IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student sheet");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        // Class Student
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Lớp");
        // Studen Code
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellValue("MSThẻ");
        // Start course
        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Thời gian diễn ra khóa học");
        // Number course
        cell = row.createCell(3, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Thứ tự khóa học");
        // Last name
        cell = row.createCell(4, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Họ");
        // First name
        cell = row.createCell(5, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Tên");
        // Sex
        cell = row.createCell(6, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Giới tính");
        // Phone
        cell = row.createCell(7, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Di động");
        // Birthday
        cell = row.createCell(8, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ngày tháng năm sinh");
        // Day birth
        cell = row.createCell(9, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ngày sinh");
        // Month birth
        cell = row.createCell(10, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Tháng sinh");
        // Company
        cell = row.createCell(11, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Công ty");
        // Email
        cell = row.createCell(12, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Email");
        // Position class
        cell = row.createCell(13, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Chức vụ MT");
        // Presenter
        cell = row.createCell(14, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Người giới thiệu");
        // Position Presenter
        cell = row.createCell(15, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Chức vụ");
        // Department
        cell = row.createCell(16, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Phòng ban");
        // Address
        cell = row.createCell(17, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Địa chỉ nhà");
        // Description
        cell = row.createCell(18, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ghi ch");
        // Area
        cell = row.createCell(19, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Khu vực");

        // Data
        if (student != null) {
            row = sheet.createRow(1);

            cell = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getStudent_code());

            cell = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getStudent_class());

            cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getStart_course());

            cell = row.createCell(3, Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(student.getNumber_course());

            cell = row.createCell(4, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getLast_name());

            cell = row.createCell(5, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getFirst_name());

            cell = row.createCell(6, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getSex());

            cell = row.createCell(7, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getPhone());

            cell = row.createCell(8, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getBirthday());

            cell = row.createCell(9, Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(student.getDay_birth());

            cell = row.createCell(10, Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(student.getMonth_birth());

            cell = row.createCell(11, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getCompany());

            cell = row.createCell(12, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getEmail());

            cell = row.createCell(13, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getPosition_class());

            cell = row.createCell(14, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getPresenter());

            cell = row.createCell(15, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getPosition_presenter());

            cell = row.createCell(16, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getDepartment());

            cell = row.createCell(17, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getAddress());

            cell = row.createCell(18, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getDescription());

            cell = row.createCell(19, Cell.CELL_TYPE_STRING);
            cell.setCellValue(student.getArea());
        }

        String pathResource = Constants.IMPORT_RESOURCE;

        File file = new File(pathResource + "/temp_export.xlsx");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
//            System.out.println("Created file: " + file.getAbsolutePath());
    }

    private void exportFileList(List<Student> students) throws FileNotFoundException, IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student sheet");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        // Class Student
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Lớp");
        // Studen Code
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellValue("MSThẻ");
        // Start course
        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Thời gian diễn ra khóa học");
        // Number course
        cell = row.createCell(3, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Thứ tự khóa học");
        // Last name
        cell = row.createCell(4, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Họ");
        // First name
        cell = row.createCell(5, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Tên");
        // Sex
        cell = row.createCell(6, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Giới tính");
        // Phone
        cell = row.createCell(7, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Di động");
        // Birthday
        cell = row.createCell(8, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ngày tháng năm sinh");
        // Day birth
        cell = row.createCell(9, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ngày sinh");
        // Month birth
        cell = row.createCell(10, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Tháng sinh");
        // Company
        cell = row.createCell(11, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Công ty");
        // Email
        cell = row.createCell(12, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Email");
        // Position class
        cell = row.createCell(13, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Chức vụ MT");
        // Presenter
        cell = row.createCell(14, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Người giới thiệu");
        // Position Presenter
        cell = row.createCell(15, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Chức vụ");
        // Department
        cell = row.createCell(16, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Phòng ban");
        // Address
        cell = row.createCell(17, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Địa chỉ nhà");
        // Description
        cell = row.createCell(18, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Ghi ch");
        // Area
        cell = row.createCell(19, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Khu vực");

        // Data
        if (students != null) {
            for (Student student : students) {
                rownum++;
                row = sheet.createRow(rownum);

                cell = row.createCell(0, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getStudent_code());

                cell = row.createCell(1, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getStudent_class());

                cell = row.createCell(2, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getStart_course());

                cell = row.createCell(3, Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(student.getNumber_course());

                cell = row.createCell(4, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getLast_name());

                cell = row.createCell(5, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getFirst_name());

                cell = row.createCell(6, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getSex());

                cell = row.createCell(7, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getPhone());

                cell = row.createCell(8, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getBirthday());

                cell = row.createCell(9, Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(student.getDay_birth());

                cell = row.createCell(10, Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(student.getMonth_birth());

                cell = row.createCell(11, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getCompany());

                cell = row.createCell(12, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getEmail());

                cell = row.createCell(13, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getPosition_class());

                cell = row.createCell(14, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getPresenter());

                cell = row.createCell(15, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getPosition_presenter());

                cell = row.createCell(16, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getDepartment());

                cell = row.createCell(17, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getAddress());

                cell = row.createCell(18, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getDescription());

                cell = row.createCell(19, Cell.CELL_TYPE_STRING);
                cell.setCellValue(student.getArea());
            }
        }

        String pathResource = Constants.IMPORT_RESOURCE;

        File file = new File(pathResource + "/temp_export.xlsx");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
//            System.out.println("Created file: " + file.getAbsolutePath());
    }
}
