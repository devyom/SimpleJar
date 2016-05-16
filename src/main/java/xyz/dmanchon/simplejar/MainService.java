/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.dmanchon.simplejar;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import managed.CassandraManagedClient;
import managed.ElasticSearchManagedClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import xyz.dmanchon.simplejar.resources.ExampleResource;

/**
 *
 * @author dmanchon
 */
public class MainService extends Application<MainConfig> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainConfig> bootstrap) {

    }
    
    @Override
    public void run(MainConfig t, Environment e) throws Exception { 
        Client client = new TransportClient()
            .addTransportAddress(new InetSocketTransportAddress(t.getElasticsearchServer(), 9300));   
        ElasticSearchManagedClient esManagedClient = new ElasticSearchManagedClient(client);
        e.lifecycle().manage(esManagedClient);
                
        Cluster cluster = Cluster.builder()                                                    // (1)
            .addContactPoint(t.getCassandraServer())
            .build();
        Session session = cluster.connect();                                           // (2)
        CassandraManagedClient cassandraManagedClient = new CassandraManagedClient(session);
        e.lifecycle().manage(cassandraManagedClient);

        ExampleResource resource = new ExampleResource(client, session);                
        e.jersey().register(resource);
    }
    
    
}
