/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import chat.Group;
import controllers.MessageController;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author rt_prem
 */
@Path("/message")
public class MessageService {
    
    MessageController mc = new MessageController();
    
//    @GET
//    @Path("{userId}")
//    @Produces("application/json")
//    public JsonArray get(@PathParam("userId") int userId) {
//        JsonArrayBuilder json = Json.createArrayBuilder();
//        for (Group g : mc.getUserMessages(userId)) {
//            json.add(g.toString());
//        }
//        return json.build();
//    }
}
