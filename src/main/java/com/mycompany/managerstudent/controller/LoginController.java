package com.mycompany.managerstudent.controller;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.services.AdminUserService;
import com.mycompany.managerstudent.services.password.PasswordService;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.SlackUtils;
import com.mycompany.managerstudent.util.Utils;
import com.nct.framework.common.LogUtil;
import com.nct.game.framework.web.view.freemarker.FreeMarker;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {

    private static final String MESSAGE_INVALID = "Username or Password invalid!";
    private static final String MESSAGE_NOT_ACTIVE = "Your account is not active!";

    private static final String PASSING_USERNAME = "username";
    private static final String PASSING_PASSWORD = "password";
    private static final String PASSING_ERROR_MESSAGE = "errorMessage";

    private static final String REDIRECT_TO_HOME_PAGE = "home";
    private static final String TAG = LoginController.class.getName();
    private static final Logger logger = LogUtil.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PASSING_USERNAME);
        String password = request.getParameter(PASSING_PASSWORD);
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.PASSING_ROOT_URL, Constants.ROOT_URL);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) { // username or password is empty
            new FreeMarker(Constants.PAGE_LOGIN, data).render(request, response);
        } else {
            try {
                AdminUser userLogin = AdminUserService.getInstance().getByUsername(username);
                if (userLogin.getUsername() == null) {
                    data.put(PASSING_ERROR_MESSAGE, MESSAGE_INVALID);
                    new FreeMarker(Constants.PAGE_LOGIN, data).render(request, response);
                } else if (userLogin.getPassword().equals(PasswordService.passwordGen(password, userLogin.getPasswordSalt()))
                        && userLogin.isActive()) { // login success
                    request.getSession().setAttribute(Constants.USER_SESSION, userLogin);
                    request.getSession().setMaxInactiveInterval(3000 * 60);
                    response.sendRedirect(REDIRECT_TO_HOME_PAGE);
                } else if (!userLogin.isActive()) { // that account is not active
                    data.put(PASSING_ERROR_MESSAGE, MESSAGE_NOT_ACTIVE);
                    new FreeMarker(Constants.PAGE_LOGIN, data).render(request, response);
                } else {
                    data.put(PASSING_ERROR_MESSAGE, MESSAGE_INVALID);
                    new FreeMarker(Constants.PAGE_LOGIN, data).render(request, response);
                }
            } catch (Exception ex) {
                SlackUtils.printDebug(TAG, ex);
                logger.error("error", ex);
            }
        }
    }

}
