package com.mycompany.managerstudent.controller;

import com.google.gson.reflect.TypeToken;
import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.services.StudentService;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.PassingDataUtil;
import com.mycompany.managerstudent.util.SlackUtils;
import com.mycompany.managerstudent.util.Utils;
import com.nct.framework.common.LogUtil;
import com.nct.framework.util.ConvertUtils;
import com.nct.framework.util.JSONUtil;
import com.nct.game.framework.web.view.freemarker.FreeMarker;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;

public class ListStudentController extends HttpServlet {

    private static final String DEFAULT_VALUE = "";
    private static final String PASSING_STUDENTS = "students";
    private static final String PASSING_ID = "id";
    private static final String PASSING_STUDENT_JSON = "student_json";
    private static final String PASSING_ACTION = "action";
    private static final String PASSING_PAGE = "page";
    private static final String PASSING_TOTAL_PAGE = "total_page";
    private static final String ACTION_ADD = "add";
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_IMPORT = "import";
    private static final String PASSING_SUCCESS = "success";
    private static final String UPDATE = "update";
    private static final String ADD = "add";
    private static final int TOTAL_ITEM = 5;
    private static final String TAG = ListStudentController.class.getName();

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
            case ACTION_ADD:
                actionAddOrUpdate(request, response, ADD);
                break;
            case ACTION_UPDATE:
                actionAddOrUpdate(request, response, UPDATE);
                break;
            case ACTION_DELETE:
                actionDelete(request, response);
                break;
            case ACTION_IMPORT:
                actionImport(request, response);
                break;
        }
    }

    private void initPage(HttpServletRequest request, HttpServletResponse response, AdminUser userSession) throws ServletException, IOException {
        try {
            String spageid = request.getParameter(PASSING_PAGE);
            int pageid = 1;
            int total = TOTAL_ITEM;

            if (StringUtils.isNotBlank(spageid)) {
                pageid = Integer.parseInt(spageid);
                pageid = pageid - 1;
                pageid = pageid * total + 1;
            }

            int total_page = StudentService.getInstance().countStudent();

            if ((total_page % total) == 0) {
                total_page = total_page / total;
            } else {
                total_page = total_page / total;
                total_page = total_page + 1;
            }

            List<Student> students = StudentService.getInstance().getAllByPage(pageid, total);

            Map<String, Object> data = new HashMap<>();
            data.put(Constants.USER_ADMIN, userSession);
            data.putAll(PassingDataUtil.generalPassing());
            data.put(PASSING_STUDENTS, students);
            data.put(PASSING_TOTAL_PAGE, total_page);

            new FreeMarker(Constants.PAGE_LIST_STUDENT, data).render(request, response);
        } catch (Exception ex) {
            SlackUtils.printDebug(TAG, ex);
            logger.error("error", ex);
        }
    }

    private void actionAddOrUpdate(HttpServletRequest request, HttpServletResponse response, String action) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            try {
                String student_json = request.getParameter(PASSING_STUDENT_JSON);
                Student student = JSONUtil.DeSerialize(student_json, Student.class);
                switch (action) {
                    case ADD:
                        StudentService.getInstance().add(student);
                        break;
                    case UPDATE:
                        StudentService.getInstance().update(student);
                        break;
                }
                obj.put(PASSING_SUCCESS, true);

            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
                obj.put(PASSING_SUCCESS, ex.getMessage());
            }
            out.print(obj);
            out.flush();
        }
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            try {
                int id = Integer.parseInt(request.getParameter(PASSING_ID));
                StudentService.getInstance().delete(id);
                obj.put(PASSING_SUCCESS, true);
            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
                obj.put(PASSING_SUCCESS, ex.getMessage());
            }
            out.print(obj);
            out.flush();
        }
    }

    private void actionImport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE_JSON);

        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            try {
                StudentService.getInstance().readFileExcel();
                obj.put(PASSING_SUCCESS, true);
            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
                obj.put(PASSING_SUCCESS, ex.getMessage());
            }
            out.print(obj);
            out.flush();
        }
    }
}
