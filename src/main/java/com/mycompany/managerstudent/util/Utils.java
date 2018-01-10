package com.mycompany.managerstudent.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author dientt
 */
public class Utils {

    private static final SimpleDateFormat formatDB = new SimpleDateFormat(Constants.DATETIME_FORMAT_DB);
    private static final SimpleDateFormat formatFolder = new SimpleDateFormat(Constants.DATETIME_FORMAT_FOLDER);
    private static final SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
    private static final SimpleDateFormat formatDateTime = new SimpleDateFormat(Constants.DATETIME_FORMAT_GAME);
    
//    public static boolean chkStr(String str) {
//        return (str != null) && (str.length() > 0);
//    }

    public static boolean isLogin(HttpSession session) {
        return session.getAttribute("user") != null;
    }
    
    public static String convertTimeStampToStr(Timestamp temstamp) {
        if(temstamp != null) {
            return formatDateTime.format(temstamp);
        }
        return "";
    }

    public static Date strToDate(String dateStr) throws ParseException {
        java.util.Date parsed = formatDB.parse(dateStr);
        return new java.sql.Date(parsed.getTime());
    }

    public static String getStrDate() {
        return formatFolder.format(Calendar.getInstance().getTime());
    }

    public static String getStrDateNormal() {
        return format.format(Calendar.getInstance().getTime());
    }

    public static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadConfigToSlack() {

        SlackUtils.sendMessage("[config]Jetty-host", Constants.HOST_LISTEN);
        SlackUtils.sendMessage("[config]Jetty-port", String.valueOf(Constants.PORT_LISTEN));
        SlackUtils.sendMessage("[config]Jetty-context_path", Constants.CONTEXT_PATH);
        SlackUtils.sendMessage("[config]Jetty-root_url", Constants.ROOT_URL);

        SlackUtils.sendMessage("[config]web_view_freemarker-template_path", Constants.PATH_TEMPLATE);

        SlackUtils.sendMessage("[config]reload-url", Constants.URL_RELOAD_URL);

        SlackUtils.sendMessage("[config]upload_path-static_folder", Constants.STATIC_FOLDER);
        SlackUtils.sendMessage("[config]upload_path-upload_resource", Constants.UPLOAD_RESOURCE);

        SlackUtils.sendMessage("[config]reload-url", Constants.URL_RELOAD_URL);
        SlackUtils.sendMessage("[config]reload-update_user_info", Constants.URL_UPDATE_USER_INFO);
        SlackUtils.sendMessage("[config]reload-get_password", Constants.URL_GET_PASSWORD);
        SlackUtils.sendMessage("[config]reload-reset_password", Constants.URL_RESET_PASSWORD);
    }

    public static String gooleReverseEngine(String scr) {
        if (scr == null) {
            return null;
        }
        String[] arr = scr.split("\\.");
        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            result.append(arr[i]).append(".");
        }
        return result.substring(0, result.length() - 1);
    }

    public static void genGameKey(int gameid, int storeid) {
        System.out.println("appId >> " + DigestUtils.md5Hex("xct_app_id_" + gameid + "@" + storeid));
        System.out.println("appKey >> " + DigestUtils.md5Hex("xct_app_key_" + gameid + "@" + storeid));
        System.out.println("appSecret >> " + DigestUtils.md5Hex("xct_app_secret_" + gameid + "@" + storeid));
    }

}
