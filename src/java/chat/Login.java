/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import javax.faces.bean.SessionScoped;
import controllers.UserController;

/**
 *
 * @author rt_prem
 */
@SessionScoped
public class Login {
    
    private String email;
    private String password;
    private boolean loggedIn;
    private User currentUser;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean doLogin() {
        String passhash = DBUtils.hash(password);
        loggedIn = false;
        currentUser = null;
        for (User u : new UserController().getUsers()) {
            if (email.equals(u.getEmail())
                    && passhash.equals(u.getPassword())) {
                loggedIn = true;
                currentUser = u;
            }
        }
        return loggedIn;
    }
}
