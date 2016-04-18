/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import chat.Login;
import controllers.UserController;
import java.util.Map;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author rt_prem
 */
@Path("/login")
public class LoginService {
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonObject post(JsonObject json) {
        JsonObject response = null;
        Login login = new Login();
        login.setEmail(json.getString("username"));
        login.setPassword(json.getString("password"));
        if(login.doLogin()) {
            response = login.getCurrentUser().toJSON();
            Cookie loginCookie = new Cookie("loginemail", login.getEmail());
        }
        return response;
    }
     @Context
    private HttpHeaders headers;
     
    @POST
    @Produces("application/json")
    public JsonObject post(String details) {
        JsonObject response = null;
        UserController uc = new UserController();
        Map<String, Cookie> cookies = headers.getCookies();
        Cookie cookie = cookies.get("loginemail");
        String email = cookie.getValue();
        uc.getUserByEmail(email).toJSON();
        return response;
    }
    
}
