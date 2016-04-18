/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author rt_prem
 */
public class Group {
    
    private int groupId;
    private String groupName;
    //private String description;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
    
    @Override
    public String toString() {
        return "{ \"groupid\" : " + groupId + 
                ", \"groupname\" : \"" + groupName 
                //"\", \"description\" : \"" + description + "\" "
                + "}";
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("groupid", groupId)
                .add("groupname", groupName)
               // .add("description", description)
                .build();
    } 
}
