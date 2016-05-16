/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import io.dropwizard.lifecycle.Managed;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 *
 * @author dmanchon
 */
public class ElasticSearchManagedClient implements Managed{
    private final Client client;
    
    public ElasticSearchManagedClient(final Client client) {
        this.client = client;         
    }
    
    @Override
    public void start() throws Exception {
        
    }

    @Override
    public void stop() throws Exception {
        client.close();
    }
    
}
