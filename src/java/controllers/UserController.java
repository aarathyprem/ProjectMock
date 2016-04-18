/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import chat.DBUtils;
import chat.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author rt_prem
 */
@ApplicationScoped
public class UserController {

    private List<User> users;

    public UserController() {
        getUsersFromDatabase();
    }

    public List<User> getUsers() {
        return users;
    }

    public String getUsernameById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u.getUserName();
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean userExists(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public String getUserNameByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u.getUserName();
            }
        }
        return null;
    }

    private void getUsersFromDatabase() {
        try {
            users = new ArrayList<>();

            // Make a Connection
            Connection conn = DBUtils.getConnection();

            // Build a Query
            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Parse the Results
            while (rs.next()) {
                User u = new User();

                u.setUserId(rs.getInt("userId"));
                u.setUserName(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setLogoutTime(rs.getDate("logoutTime"));
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addUser(User user) {
        boolean success = false;
        if (!userExists(user.getEmail())) {
            try (Connection conn = DBUtils.getConnection()) {
                String passhash = DBUtils.hash(user.getPassword());
                String sql = "INSERT INTO users (userName, password, logoutTime, email, authUser) VALUES(?, ?, NOW(), ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUserName());
                pstmt.setString(2, passhash);
                pstmt.setString(3, user.getEmail());
                pstmt.setBoolean(4, user.isAuthUser());
                System.out.println(sql);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            getUsersFromDatabase();
            success = true;
        }
        return success;
    }
}
