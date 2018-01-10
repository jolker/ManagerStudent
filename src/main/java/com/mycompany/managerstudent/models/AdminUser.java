package com.mycompany.managerstudent.models;

/**
 *
 * @author dientt
 */
public class AdminUser {
    private String username;
    private String password;
    private boolean active;
    private String passwordSalt;
    
    public AdminUser() {
    }

    public AdminUser(String username, String password, boolean active, String passwordSalt) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.passwordSalt = passwordSalt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return "AdminUser{" + "username=" + username + ", password=" + password + '}';
    }
    
}
