package com.mycompany.managerstudent.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dientt
 */
public enum Role {
    Admin(1),
    AdminNews(3),
    Other(2);
    
    private final int id;

    private Role(int id) {
        this.id = id;
    }
    
    public int getRoleId() {
        return this.id;
    }
    
    public static String getNameById(int id) { 
        for(Role e : Role.values()){
            if(id == e.id) {
                return e.name();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> getRoleMap() {
        Map<Integer, String> retval = new HashMap<>();
        Role[] role = Role.values();
        for(int i=0; i<role.length; i++) {
            retval.put(role[i].getRoleId(), getNameById(role[i].getRoleId()));
        }
        return retval;
    }
}
