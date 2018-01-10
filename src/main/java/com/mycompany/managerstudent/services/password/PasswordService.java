package com.mycompany.managerstudent.services.password;
/**
 *
 * @author dientt
 */
public class PasswordService {
    public static String passwordGen(String orgPass, String passSalt) {
        return Hash.getInstance().hashMessage(passSalt + Hash.getInstance().hashMessage(orgPass, true), true);
    }
    
//    public static void main(String agrs[]) {
//        String salt = "37ZVAxJB";
//        String pass = "XCT#Admin@123";
//        System.out.println(passwordGen(pass, salt));
//    }
    
}
