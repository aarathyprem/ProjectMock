/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import chat.Group;
import controllers.GroupController;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



/**
 *
 * @author rt_prem
 */
@Path("/group")
public class GroupService {
    
    GroupController gc = new GroupController();
    
    @GET
    @Path("{email}")
    @Produces("application/json")
    public JsonArray get(@PathParam("email") String email) {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Group g : gc.getUserGroupsByEmail(email)) {
            json.add(g.toString());
        }
        return json.build();
    }
}
