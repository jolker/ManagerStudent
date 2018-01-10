/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.controller;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.services.AdminUserService;
import com.mycompany.managerstudent.services.StudentService;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.PassingDataUtil;
import com.mycompany.managerstudent.util.SlackUtils;
import com.nct.framework.common.LogUtil;
import com.nct.framework.util.ConvertUtils;
import com.nct.game.framework.web.view.freemarker.FreeMarker;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.json.simple.JSONObject;

/**
 *
 * @author anhlnt
 */
public class AdminUserController extends HttpServlet {

    private static final String DEFAULT_VALUE = "";
    private static final String PASSING_USERNAME = "username";
    private static final String PASSING_PASSWORD = "password";
    private static final String PASSING_ACTIVE = "active";
    private static final String PASSING_ACTION = "action";
    private static final String PASSING_RESULT = "result";
    private static final String PASSING_DUPLICATE = "duplicate";
    private static final String PASSING_USERS = "users";
    private static final String PASSING_MESSAGE_EXIST_USER = "Username already exists!";
    private static final String PASSING_MESSAGE_ERROR = "Error from server!";
    private static final String SUCCESS = "success";
    private static final String ACTION_ADD = "add";
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_DELETE = "delete";
    private static final String PASSING_PAGE = "page";
    private static final String PASSING_TOTAL_PAGE = "total_page";
    private static final int TOTAL_ITEM = 5;

    private static final String TAG = AdminUserController.class.getName();

    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(AdminUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(PASSING_ACTION);
        AdminUser userSession = (AdminUser) request.getSession().getAttribute(Constants.USER_SESSION);

        switch (StringUtils.isBlank(action) ? DEFAULT_VALUE : action) {
            case DEFAULT_VALUE:
                initPage(request, response, userSession);
                break;
            case ACTION_ADD:
                actionAddOrUpdate(request, response, ACTION_ADD);
                break;
            case ACTION_UPDATE:
                actionAddOrUpdate(request, response, ACTION_UPDATE);
                break;
            case ACTION_DELETE:
                actionDelete(request, response);
                break;

        }
    }

    private void initPage(HttpServletRequest request, HttpServletResponse response, AdminUser userSession) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.putAll(PassingDataUtil.generalPassing());
        if (userSession != null) {
            try {
                String spageid = request.getParameter(PASSING_PAGE);
                int pageid = 1;
                int total = TOTAL_ITEM;

                if (StringUtils.isNotBlank(spageid)) {
                    pageid = Integer.parseInt(spageid);
                    pageid = pageid - 1;
                    pageid = pageid * total + 1;
                }

                int total_page = AdminUserService.getInstance().countAdmin();

                if ((total_page % total) == 0) {
                    total_page = total_page / total;
                } else {
                    total_page = total_page / total;
                    total_page = total_page + 1;
                }

                List<AdminUser> adminUsers = new ArrayList<>();
                adminUsers.addAll(AdminUserService.getInstance().getAllByPage(pageid, total));
                data.put(Constants.USER_ADMIN, userSession);
                data.put(PASSING_USERS, adminUsers);
                data.put(PASSING_TOTAL_PAGE, total_page);
                new FreeMarker(Constants.PAGE_USER_ADMIN, data).render(request, response);
            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
            }
        }
    }

    private void actionAddOrUpdate(HttpServletRequest request, HttpServletResponse response, String action) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            try {
                String username = request.getParameter(PASSING_USERNAME);
                String password = request.getParameter(PASSING_PASSWORD);
                boolean active = ConvertUtils.toBoolean(request.getParameter(PASSING_ACTIVE), true);

                switch (action) {
                    case ACTION_ADD:
                        AdminUser adminUser = new AdminUser();
                        adminUser.setActive(active);
                        adminUser.setUsername(username);
                        adminUser.setPassword(password);
                        AdminUserService.getInstance().add(adminUser);
                        break;
                    case ACTION_UPDATE:
                        AdminUser oldUser = AdminUserService.getInstance().getByUsername(username);
                        oldUser.setActive(active);
                        oldUser.setPassword(password);
                        AdminUserService.getInstance().update(oldUser);
                        break;
                }

                obj.put(PASSING_RESULT, SUCCESS);
                obj.put(PASSING_DUPLICATE, "");
            } catch (DuplicateKeyException de) {
                obj.put(PASSING_DUPLICATE, PASSING_MESSAGE_EXIST_USER);
                obj.put(PASSING_RESULT, "");
            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
                obj.put(PASSING_DUPLICATE, "");
                obj.put(PASSING_RESULT, PASSING_MESSAGE_ERROR);
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
                String username = request.getParameter(PASSING_USERNAME);
                AdminUserService.getInstance().delete(username);
                obj.put(PASSING_RESULT, true);
            } catch (Exception ex) {
                obj.put(PASSING_RESULT, ex.getMessage());
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
            }
            out.print(obj);
            out.flush();
        }
    }
}
