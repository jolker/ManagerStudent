package com.mycompany.managerstudent.DB;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.SlackUtils;
import com.nct.framework.common.LogUtil;
import com.nct.framework.dbconn.ClientManager;
import com.nct.framework.dbconn.ManagerIF;
import com.nct.framework.util.ConvertUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;

public class AdminUserDB {
    private static final String TAG = AdminUserDB.class.getName();
    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(AdminUserDB.class);

    private static final AdminUserDB INSTANCE = new AdminUserDB();

    private static final String QUERY_GET_ALL = "SELECT * FROM %s";
    
    private static final String QUERY_GET_ALL_BY_PAGE = "SELECT * FROM USER_ACCOUNT LIMIT ?, ?";

    private static final String QUERY_GET_COUNT_ADMIN = "SELECT COUNT(*) FROM USER_ACCOUNT";

    private static final String QUERY_INSERT = "INSERT INTO %s (USER_NAME, PASSWORD, ACTIVE, PASSWORD_SALT) VALUES(?, ?, ?, ?)";
    
    private static final String QUERY_UPDATE = "UPDATE %s SET PASSWORD=?, ACTIVE=? WHERE USER_NAME=?";
    
    private static final String QUERY_UPDATE_2 = "UPDATE %s SET ACTIVE=? WHERE USER_NAME=?";
    
    private static final String QUERY_DELETE = "DELETE FROM %s WHERE USER_NAME=?";
    
    private static final String QUERY_GET_BY_USERNAME = "SELECT * FROM %s WHERE USER_NAME=?";
    
    private static final int MYSQL_DUPLICATE_PK = 1062;
    
    private static final String DB = Constants.DB_REPORT;
    
    private static final String TABLE_NAME = "USER_ACCOUNT";
    
    private AdminUserDB() {

    }

    public static AdminUserDB getInstance() {
        return INSTANCE;
    }

    public List<AdminUser> getAll() throws Exception {
        List<AdminUser> users = new ArrayList<>();
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(DB);
            cnn = cm.borrowClient();
            String url = String.format(QUERY_GET_ALL, TABLE_NAME);
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        users.add(getResultSet(rs));
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
        return users;
    }

    public void add(AdminUser adminUser) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(DB);
            cnn = cm.borrowClient();
            String url = String.format(QUERY_INSERT, TABLE_NAME);
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                stmt.setString(1, adminUser.getUsername());
                stmt.setString(2, adminUser.getPassword());
                stmt.setBoolean(3, adminUser.isActive());
                stmt.setString(4, adminUser.getPasswordSalt());
                stmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == MYSQL_DUPLICATE_PK) {
                throw new DuplicateKeyException(sqle.getMessage());
            }
            logger.error("error", sqle);
            SlackUtils.printDebug(TAG, sqle);
            throw new SQLException();
        } catch (Exception ex) {
            SlackUtils.printDebug(TAG, ex);
            logger.error("error", ex);
            throw new Exception();
        } finally {
            if (cm != null && cnn != null) {
                cm.returnClient(cnn);
            }
        }
    }
    
    public AdminUser getByUsername(String username) throws Exception{
        
        ManagerIF cm = null;
        Connection cnn = null;
        AdminUser adminUser = null;
        try {
            cm = ClientManager.getInstance(DB);
            
            cnn = cm.borrowClient();
            
            String url = String.format(QUERY_GET_BY_USERNAME, TABLE_NAME);
            
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    
                     while (rs.next()) {
                        adminUser = getResultSet(rs);
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
        
        return adminUser;
    }
    
    public void update(AdminUser adminUser) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(DB);
            cnn = cm.borrowClient();
            String url = String.format(QUERY_UPDATE, TABLE_NAME);
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                stmt.setString(1, adminUser.getPassword());
                stmt.setBoolean(2, adminUser.isActive());
                stmt.setString(3, adminUser.getUsername());
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
    
    public void update2(AdminUser adminUser) throws Exception { // without password
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(DB);
            cnn = cm.borrowClient();
            String url = String.format(QUERY_UPDATE_2, TABLE_NAME);
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                stmt.setBoolean(1, adminUser.isActive());
                stmt.setString(2, adminUser.getUsername());
                
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
    
    public void delete(String username) throws Exception {
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(DB);
            cnn = cm.borrowClient();
            String url = String.format(QUERY_DELETE, TABLE_NAME);
            try (PreparedStatement stmt = cnn.prepareStatement(url)) {
                stmt.setString(1, username);                
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
    
    private AdminUser getResultSet(ResultSet rs) throws SQLException {
        AdminUser adminUser = new AdminUser();
        String username = ConvertUtils.toString(rs.getString("USER_NAME"), "");
        String password = ConvertUtils.toString(rs.getString("PASSWORD"), "");
        String passwordSalt = ConvertUtils.toString(rs.getString("PASSWORD_SALT"), "");
        boolean active = rs.getBoolean("ACTIVE");
        
        adminUser.setPassword(password);
        adminUser.setUsername(username);
        adminUser.setActive(active);
        adminUser.setPasswordSalt(passwordSalt);
        return adminUser;
    }

    
    public int countAdmin() throws Exception {
        int result = 0;
        ManagerIF cm = null;
        Connection cnn = null;
        try {
            cm = ClientManager.getInstance(Constants.DB_REPORT);
            cnn = cm.borrowClient();

            try (PreparedStatement stmt = cnn.prepareStatement(QUERY_GET_COUNT_ADMIN, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

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

    public List<AdminUser> getAllByPage(int start, int total) throws Exception {
        List<AdminUser> users = new ArrayList<>();
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
                        users.add(getResultSet(rs));
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
        return users;
    }
}
