/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import io.dropwizard.lifecycle.Managed;

/**
 *
 * @author dmanchon
 */
public class CassandraManagedClient implements Managed {

    private final Session session;

    public CassandraManagedClient(Session session) {
        this.session = session;
    }
    
    @Override
    public void start() throws Exception {
        
    }

    @Override
    public void stop() throws Exception {
        Cluster cluster = session.getCluster();
        session.close();
        cluster.close();
        
    }
    
}
