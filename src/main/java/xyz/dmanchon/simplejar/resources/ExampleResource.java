/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.dmanchon.simplejar.resources;

import com.codahale.metrics.annotation.Timed;
import com.datastax.driver.core.Session;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.elasticsearch.client.Client;

/**
 *
 * @author dmanchon
 */
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

    private final Client client;
    private final Session session;
    
    public ExampleResource(Client client, Session session) {
        this.client = client;
        this.session = session;
    }
    
    @POST
    @Path("store/{name}")
    public void store(@PathParam("name") String name) {
        session.execute(
                "CREATE KEYSPACE IF NOT EXISTS test\n" +
                "WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
        
        session.execute(
                "CREATE TABLE IF NOT EXISTS test.names (\n" +
                "  name varchar PRIMARY KEY,\n" +
                "  id varchar\n" +
                ");");
        
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVersion()
    {
        return "v1";
    }
}
