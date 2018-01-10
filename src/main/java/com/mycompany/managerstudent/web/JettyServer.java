package com.mycompany.managerstudent.web;

import com.mycompany.managerstudent.controller.AdminUserController;
import com.mycompany.managerstudent.controller.DownloadFileServlet;
import com.mycompany.managerstudent.controller.HomeController;
import com.mycompany.managerstudent.controller.ImportFileController;
import com.mycompany.managerstudent.controller.ListStudentController;
import com.mycompany.managerstudent.controller.LoginController;
import com.mycompany.managerstudent.controller.LogoutController;
import com.mycompany.managerstudent.controller.SearchController;
import com.mycompany.managerstudent.controller.UploadImageController;
import com.nct.framework.common.Config;
import com.nct.framework.util.ConvertUtils;
import com.nct.game.framework.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServer {

    private Server server;
    private ServletContextHandler servletContextHandler;

    public JettyServer() {
        init();
    }

    private void init() {
        try {
            servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            QueuedThreadPool threadPool = new QueuedThreadPool();
            threadPool.setMaxThreads(ConvertUtils.toInt(Config.getParam("jetty", "max_thread"), 10));
            threadPool.setMinThreads(ConvertUtils.toInt(Config.getParam("jetty", "min_thread"), 200));
            this.server = new Server();
            this.server.setThreadPool(threadPool);

            servletContextHandler.getSessionHandler().getSessionManager().setMaxInactiveInterval(ConvertUtils.toInt(Config.getParam("jetty", "session_timeout"), 90000));
            servletContextHandler.setContextPath(ConvertUtils.toString(Config.getParam("jetty", "context_path"), "/"));
//            servletContextHandler.addFilter(SessionFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));

            servletContextHandler.addServlet(LoginController.class, "/login");
            servletContextHandler.addServlet(LogoutController.class, "/logout");
            servletContextHandler.addServlet(HomeController.class, "/home");
            servletContextHandler.addServlet(ListStudentController.class, "/listStudent");
            servletContextHandler.addServlet(UploadImageController.class, "/uploadImage");
            servletContextHandler.addServlet(ImportFileController.class, "/importFile");
            servletContextHandler.addServlet(SearchController.class, "/search");
            servletContextHandler.addServlet(AdminUserController.class, "/userAdminTool");
            servletContextHandler.addServlet(DownloadFileServlet.class, "/downloadFile");

            ResourceHandler resourceHandler = new ResourceHandler();

            resourceHandler.setWelcomeFiles(new String[]{"login.html"});
            resourceHandler.setDirectoriesListed(false);
            String resouces = ConvertUtils.toString(Config.getParam("jetty", "resource_base"), ".");
            resourceHandler.setResourceBase(resouces);

            RewriteHandler rewriteHandler = new RewriteHandler();
            rewriteHandler.setRewriteRequestURI(true);
            rewriteHandler.setRewritePathInfo(true);
            rewriteHandler.setOriginalPathAttribute("requestedPath");

            ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
            errorHandler.setShowMessageInTitle(false);
            errorHandler.addErrorPage(404, "/page-not-found");
            errorHandler.addErrorPage(500, "/error-from-server");
            servletContextHandler.setErrorHandler(errorHandler);

            HandlerList handlers = new HandlerList();
            handlers.addHandler(resourceHandler);
            handlers.addHandler(servletContextHandler);

            SelectChannelConnector connector = new SelectChannelConnector();
            String host = Config.getParam("jetty", "host");
            int port = ConvertUtils.toInt(Config.getParam("jetty", "port"), 80);
            int idle = ConvertUtils.toInt(Config.getParam("jetty", "idle"), 5) * 1000;

            connector.setHost(host);
            connector.setPort(port);
//            connector.setIdleTimeout(idle);

            server.setConnectors(new Connector[]{connector});
            server.setHandler(handlers);
            server.setStopAtShutdown(true);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() throws Exception {
        try {
            this.server.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            throw e;
        }
    }

    public void join() {
        try {
            this.server.join();
        } catch (Exception e) {
        }
    }

    public void shutdown() {
        try {
            if (server != null && server.isStarted()) {
                server.stop();
            } else {
            }
        } catch (Exception e) {
        }
    }
}
