/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import chat.DBUtils;
import chat.Message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rt_prem
 */
public class MessageController {
    List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public MessageController() {
        getMessagesFromDatabase();
    }
    
    private void getMessagesFromDatabase() {
        try {
            messages = new ArrayList<>();

            // Make a Connection
            Connection conn = DBUtils.getConnection();

            // Build a Query
            String sql = "SELECT messageId, message, username FROM messages JOIN users u ON u.userId = ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

//            // Parse the Results
//            while (rs.next()) {
//                Group g = new Group();
//                g.setGroupId(rs.getInt("groupId"));
//                g.setGroupName(rs.getString("groupName"));
//                g.setDescription(rs.getString("description"));
//                groups.add(g);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public Group getGroupById(int id) {
//        for (Group g : groups) {
//            if (g.getGroupId() == id) {
//                return g;
//            }
//        }
//        return null;
//    }
}
