/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import chat.User;
import controllers.UserController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author rt_pr
 */
@Path("/register")
public class RegisterService {

    UserController uc = new UserController();

    @POST
    @Consumes("application/json")
    public boolean post(JsonObject json) {
        User user = new User();
        user.setUserName(json.getString("username"));
        user.setPassword(json.getString("password"));
        user.setEmail(json.getString("email"));
        try {
            user.setLogoutTime(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setAuthUser(false);

        return uc.addUser(user);
    }
}
