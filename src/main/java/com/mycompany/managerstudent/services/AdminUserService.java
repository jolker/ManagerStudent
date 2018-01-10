package com.mycompany.managerstudent.services;

import com.nct.framework.common.LogUtil;
import com.mycompany.managerstudent.DB.AdminUserDB;
import com.mycompany.managerstudent.DB.StudentDB;
import com.mycompany.managerstudent.interfaces.IAdminUser;
import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.services.password.PasswordService;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;

/**
 *
 * @author dientt
 */
public class AdminUserService implements IAdminUser {

    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(AdminUserService.class);

    private static final AdminUserService INSTANCE = new AdminUserService();

    private AdminUserService() {

    }

    public static AdminUserService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<AdminUser> getAll() throws Exception {
        try {
            return AdminUserDB.getInstance().getAll();
        } catch (Exception ex) {
            logger.error("error", ex);
            throw new Exception();
        }
    }

    @Override
    public void add(AdminUser adminUser) throws Exception {
        try {
            String randomPasswordSalt = RandomStringUtils.random(8, true, true);
            adminUser.setPasswordSalt(randomPasswordSalt);
            String password = PasswordService.passwordGen(adminUser.getPassword(), randomPasswordSalt);
            adminUser.setPassword(password);
            AdminUserDB.getInstance().add(adminUser);
        } catch (DuplicateKeyException ex) {
            logger.error("error", ex);
            throw new DuplicateKeyException();
        } catch (Exception e) {
            logger.error("error", e);
            throw new Exception();
        }
    }

    @Override
    public AdminUser getByUsername(String username) throws Exception {
        try {
            return AdminUserDB.getInstance().getByUsername(username);
        } catch (Exception ex) {
            logger.error("error", ex);
            throw new Exception();
        }
    }

    @Override
    public void update(AdminUser adminUser) throws Exception {
        try {
            if(StringUtils.isNotBlank(adminUser.getPassword())){
                adminUser.setPassword(PasswordService.passwordGen(adminUser.getPassword(), adminUser.getPasswordSalt()));
                AdminUserDB.getInstance().update(adminUser);
            } else { // without password
                AdminUserDB.getInstance().update2(adminUser);
            }
        } catch (Exception ex) {
            logger.error("error", ex);
            throw new Exception();
        }
    }

    @Override
    public void delete(String username) throws Exception {
        try {
            AdminUserDB.getInstance().delete(username);
        } catch (Exception ex) {
            logger.error("error", ex);
            throw new Exception();
        }
    }
    
    @Override
    public int countAdmin() throws Exception {
        return AdminUserDB.getInstance().countAdmin();
    }

    @Override
    public List<AdminUser> getAllByPage(int start, int total) throws Exception {
        return AdminUserDB.getInstance().getAllByPage(start, total);
    }

}
