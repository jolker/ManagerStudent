package com.mycompany.managerstudent.util;

import com.mycompany.managerstudent.enums.Role;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dientt
 */
public class PassingDataUtil {
    private static Map<String, Object> mapData = new HashMap<>();
    
    public static Map<String, Object> generalPassing() {
        if(mapData.isEmpty()) {
            Map<String, Object> passingData = new HashMap<>();
            passingData.put(Constants.PASSING_ROOT_URL, Constants.ROOT_URL);
            for(Role role : Role.values()) {
                passingData.put(role.name(), role.getRoleId());
            }
            mapData.putAll(passingData);
            return passingData;
        } else {
            return mapData;
        }
    }
}
