package com.mycompany.managerstudent.controller;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.PassingDataUtil;
import com.nct.game.framework.web.view.freemarker.FreeMarker;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        Map<String, Object> data = new HashMap<>();
        data.putAll(PassingDataUtil.generalPassing());
        AdminUser userSession = (AdminUser) request.getSession().getAttribute(Constants.USER_SESSION);
        if (userSession != null) {
            data.put(Constants.USER_ADMIN, userSession);
            new FreeMarker(Constants.PAGE_MAIN, data).render(request, response);
        }
    }
    
}
