package com.mycompany.managerstudent.util;

import com.nct.framework.common.Config;
import com.nct.framework.util.ConvertUtils;
import com.nct.game.framework.util.ConvertUtil;

/**
 *
 * @author hoannv
 */
public class Constants {
    
    public static final int PAGE_SIZE = 20;
    
    public static final String DB_REPORT = "db_quanlyhs";
    public static final String DB_TAOTHAO = "db_taothao";
    public static final String SLACK_URL = Config.getParam("web", "slack_url");
    public static final int MIN_THREAD = ConvertUtils.toInt(Config.getParam("jetty", "min_thread"), 8);
    public static final int MAX_THREAD = ConvertUtils.toInt(Config.getParam("jetty", "max_thread"), 512);
    public static final String HOST_LISTEN = ConvertUtils.toString(Config.getParam("jetty", "host"), "localhost");
    public static final int PORT_LISTEN = ConvertUtils.toInt(Config.getParam("jetty", "port"), 1101);
    public static final String ROOT_URL = ConvertUtils.toString(Config.getParam("jetty", "root_url"), "http://console.nivi.vn/");
    public static final String RESOURCE_PATH = Config.getParam("jetty", "resource_path");
    
    public static final String CONTEXT_PATH = ConvertUtils.toString(Config.getParam("jetty", "context_path"), "");
    public static final String CURRENT_DIR = new java.io.File(".").getAbsolutePath() + "/";
    
    public static final String SESSION_USER_LOGIN = "loginUser";
    
    public static final String GOOGLE_KEY_DEFAULT = ConvertUtils.toString(Config.getParam("game_properties", "google_key"), "");
    public static final String APPLE_KEY_DEFAULT = ConvertUtils.toString(Config.getParam("game_properties", "apple_key"), "");
    public static final String APPFLYER_DEV_KEY_DEFAULT = ConvertUtils.toString(Config.getParam("game_properties", "appflyer_dev_key"), "");
    
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "hh:mm:ss";
    public static final String DATETIME_FORMAT_DB = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_FOLDER = "yyyyMMdd";
    
    public static final String DATETIME_FORMAT_REPORT = "d/M";
    
    public static final String DATETIME_FORMAT_COPYRIGHT = "yyyy-MM-dd HH:mm";
    
    public static final String DATETIME_FORMAT_GAME = "yyyy-MM-dd hh:mm:ss";

    public static final String HOST = Config.getParam("web", "host");
    public static final int PORT = ConvertUtils.toInt(Config.getParam("web", "port"));
    public static final String DEPLOY = Config.getParam("web", "deploy");
    public static final int MAX_THREADS = ConvertUtils.toInt(Config.getParam("web", "max_threads"));
    public static final int MIN_THREADS = ConvertUtils.toInt(Config.getParam("web", "min_threads"), 10);

    public static final int HTTP_OUTPUT_BUFFER_SIZE = ConvertUtil.toInt(Config.getParam("config_http", "output_buffer_size"), 32768);
    public static final int HTTP_REQUEST_HEADER_SIZE = ConvertUtil.toInt(Config.getParam("config_http", "request_header_size"), 8192);
    public static final int HTTP_RESPONSE_HEADER_SIZE = ConvertUtil.toInt(Config.getParam("config_http", "response_header_size"), 8192);
    
    public static final int USER_LIMIT = 20;
        
    public static final String PAGE_LOGIN = "pages/loginPage";
    public static final String PAGE_MAIN = "pages/mainPage";
    public static final String PAGE_LIST_STUDENT = "pages/listStudentPage";
    public static final String PAGE_SEARCH_STUDENT = "pages/searchPage";
    public static final String PAGE_KEY_TOOL = "pages/keyToolPage";
    public static final String PAGE_USER = "pages/userPage";
    public static final String PAGE_USER_ADMIN = "pages/userAdminToolPage";
    public static final String PAGE_PLAYERS = "pages/userGamePage";
    public static final String PAGE_PAYMENT = "pages/paymentPage";
    public static final String PAGE_GAME_KEY = "pages/gameKeyPage";
    public static final String PAGE_GAME_CONFIG = "pages/gameConfigPage";
    public static final String PAGE_GAME = "pages/gamePage";
    public static final String PAGE_ERROR = "pages/errorPage";
    public static final String PAGE_THONGBAO = "pages/thongbao";
    public static final String PAGE_MANAGEMENT = "pages/managementPage";
    public static final String PAGE_NEWS = "pages/newsPage";
    public static final String PAGE_PROFILE = "pages/profilePage";
    public static final String PAGE_GIFTCODE_MANAGEMENT = "pages/giftcodeManagement";
    public static final String PAGE_SUPPORT = "pages/supportPage";
    
    public static final String ISADMIN = "isAdmin";
    public static final String ADMIN = "admin";
    public static final String USER_SESSION = "user";
    public static final String PASSING_MESSAGE = "message";
    public static final String PASSING_ADMIN_ROLE = "admin_role";
    public static final String PASSING_ADMIN_NEWS_ROLE = "admin_news_role";
    public static final String PASSING_OTHER_ROLE = "other_role";
    public static final String PASSING_ERROR = "error";
    public static final String USER_ADMIN = "user";
    public static final String MESSAGE = "message";
    public static final String MESSAGE_SERVER_ERROR = "500 Server Error";
    public static final String MESSAGE_PAGE_NOT_FOUND = "404 Page not found";
    public static final String MESSAGE_NOT_PERMISSION = "You don't have permission to access this source";
    public static final String ERROR_DUPLICATE = "Duplicate";
    public static final String ERROR_SERVER = "500 Server error.";
    
    public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
    public static final String CONTENT_TYPE_TEXT = "text/html; charset=UTF-8";
    public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";
    
    public static final String MAP_DATA_LEFT_SIDE_BAR = "main";
    public static final String MAP_DATA_PAGE_ACCOUNT_MANAGEMENT = "accountManagement";
    public static final String MAP_DATA_PAGE_PROFILE = "profile";
    
    public static final String PASSING_ROOT_URL = "root_url";
    
    public static final String PATH_TEMPLATE = Config.getParam("web_view_freemarker", "template_path");
    public static final String KEY_TOOL_PATH = Config.getParam("upload_path", "key_tool_path");
    public static final String UPLOAD_RESOURCE = Config.getParam("upload_path", "upload_resource");
    public static final String IMPORT_RESOURCE = Config.getParam("import_path", "upload_resource");
    public static final String STATIC_FOLDER = ConvertUtils.toString(Config.getParam("upload_path", "static_folder"), "");
    public static final String APK_FOLDER = ConvertUtils.toString(Config.getParam("upload_path", "apk_folder"), "");
    public static final String IMG_FOLDER = ConvertUtils.toString(Config.getParam("upload_path", "img_folder"), "");
    public static final String URL_RELOAD_URL = ConvertUtils.toString(Config.getParam("reload", "url"), "http://api.nivi.vn/admin/reload?type=%s");
    public static final String URL_UPDATE_USER_INFO  = ConvertUtils.toString(Config.getParam("reload", "update_user_info"));
    public static final String URL_GET_PASSWORD  = ConvertUtils.toString(Config.getParam("reload", "get_password"));
    public static final String URL_RESET_PASSWORD  = ConvertUtils.toString(Config.getParam("reload", "reset_password"));
    
    public static final String PASSING_URL_RELOAD = "url_reload";
    
    public static final String TYPE_STRING = String.class.getName();
    public static final String TYPE_INTEGER = Integer.class.getName();
    public static final String TYPE_BOOLEAN = Boolean.class.getName();
    public static final String TYPE_DATE = java.util.Date.class.getName();
    public static final String TYPE_LONG = Long.class.getName();
    public static final String TYPE_FLOAT = Float.class.getName();
    public static final String ALL = "ALL";
    
    public static final String ACTION_MANAGEMENT = "/management";
    public static final String ACTION_USER_ADMIN_TOOL = "/userAdminTool";
    public static final String ACTION_LOGIN = "/login";
    public static final String ACTION_EXPORT = "/export";
    public static final String ACTION_LOGOUT = "/logout";
    public static final String ACTION_HOME = "/home";
    public static final String ACTION_USER = "/user";
    public static final String ACTION_PAYMENT = "/payment";
    public static final String ACTION_GAME_KEY = "/gameKey";
    public static final String ACTION_GAME_CONFIG = "/gameConfig";
    public static final String ACTION_GAME = "/game";
    public static final String ACTION_PROFILE = "/profile";
    public static final String ACTION_UPLOAD_IMAGE = "/uploadImage";
    public static final String ACTION_NEWS = "/newsAdminTool";
    public static final String ACTION_NEWSx = "/giftcodeManagement";
    public static final String ACTION_NOT_FOUND = "/page-not-found";
    public static final String ACTION_ERROR_FROM_SERVER = "/error-from-server";
    public static final String ACTION_CONFIG = "/config";
    public static final String ACTION_KEY_TOOL = "/keyTool";
}
