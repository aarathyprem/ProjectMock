/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author rt_prem
 */
public class User {
    
    private int userId;
    private String userName;
    private String email;
    private String password;
    private Date logoutTime;
    private boolean authUser;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public boolean isAuthUser() {
        return authUser;
    }

    public void setAuthUser(boolean authUser) {
        this.authUser = authUser;
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("userid", userId)
                .add("username", userName)
                .add("email", email)
                .add("logouttime", logoutTime.toString())
                .add("password", password)
                .add("authuser", authUser)
                .build();
    } 
}
