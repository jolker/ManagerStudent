package com.mycompany.managerstudent.web;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.util.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cangnpk
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        String pathUri = ((HttpServletRequest) request).getRequestURI().toString();
        AdminUser user = (AdminUser) httpSession.getAttribute(Constants.USER_SESSION);
        
        if (Constants.CONTEXT_PATH.length() > 0) {
            pathUri = pathUri.replace(Constants.CONTEXT_PATH, "/");
        }
        if(pathUri.contains("login")) { // for login action
            chain.doFilter(request, response);
        } else {
            if(user != null) {
                chain.doFilter(request, response);
            } else { // not login yet or this account is not active
                ((HttpServletResponse) response).sendRedirect(Constants.ROOT_URL);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
