package com.mycompany.managerstudent.interfaces;

import com.mycompany.managerstudent.models.AdminUser;
import com.mycompany.managerstudent.models.Student;
import java.util.List;

/**
 *
 * @author dientt
 */
public interface IAdminUser {

    public List<AdminUser> getAll() throws Exception;

    public void add(AdminUser adminUser) throws Exception;

    public void update(AdminUser adminUser) throws Exception;

    public void delete(String username) throws Exception;

    public AdminUser getByUsername(String username) throws Exception;

    public int countAdmin() throws Exception;
    
    public List<AdminUser> getAllByPage(int start,int total) throws Exception;
}
