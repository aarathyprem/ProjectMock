/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import chat.DBUtils;
import chat.Group;
import chat.User;
import java.sql.Connection;
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
public class GroupController {

    List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public GroupController() {
       // getGroupsFromDatabase();
    }

    private void getGroupsFromDatabase() {
        try {
            groups = new ArrayList<>();

            // Make a Connection
            Connection conn = DBUtils.getConnection();

            // Build a Query
            String sql = "SELECT * FROM groups";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Parse the Results
            while (rs.next()) {
                Group g = new Group();
                g.setGroupId(rs.getInt("groupId"));
                g.setGroupName(rs.getString("groupName"));
               // g.setDescription(rs.getString("description"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Group getGroupById(int id) {
        for (Group g : groups) {
            if (g.getGroupId() == id) {
                return g;
            }
        }
        return null;
    }

    public void addGroup(Group group) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO groups (groupName) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, group.getGroupName());
          //  pstmt.setString(2, group.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getGroupsFromDatabase();
    }

    public List<User> getGroupMembers(int groupId) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT userId, userName, password, email, logoutTime "
                    + "FROM usergroups ug JOIN groups g ON ug.groupId = g.groupId "
                    + "JOIN users u ON ug.userId = u.userId WHERE ug.groupId = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setUserName(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                // u.setLogoutTime(rs.getDate("logoutTime"));
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public List<Group> getUserGroups(int userId) {
        List<Group> ugroups = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT ug.groupId, groupName"
                    + "FROM usergroups ug JOIN groups g ON ug.groupId = g.groupId "
                    + "JOIN users u ON ug.userId = u.userId WHERE ug.userId = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Group g = new Group();
                g.setGroupId(rs.getInt("groupId"));
                g.setGroupName(rs.getString("groupName"));
              //  g.setDescription(rs.getString("description"));
                ugroups.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ugroups;
    }
    
    public List<Group> getUserGroupsByEmail(String email) {
        List<Group> ugroups = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT ug.groupId, g.groupName "
                    + "FROM usergroups ug JOIN groups g ON ug.groupId = g.groupId "
                    + "JOIN users u ON ug.userId = u.userId WHERE u.email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                Group g = new Group();
                g.setGroupId(rs.getInt("groupId"));
                g.setGroupName(rs.getString("groupName"));
              //  g.setDescription(rs.getString("description"));
                ugroups.add(g);
                System.out.println(g.getGroupName());
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ugroups;
    }
}
