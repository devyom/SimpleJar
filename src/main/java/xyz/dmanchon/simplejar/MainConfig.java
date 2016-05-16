/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.dmanchon.simplejar;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author dmanchon
 */
public class MainConfig extends Configuration {
    @NotEmpty
    private String cassandraServer = "localhost";

    @JsonProperty
    public String getCassandraServer() {
        return cassandraServer;
    }

    @JsonProperty
    public void setCassandraServer(String server) {
        this.cassandraServer = server;
    }

    @NotEmpty
    private String elasticsearchServer = "localhost";

    @JsonProperty
    public String getElasticsearchServer() {
        return elasticsearchServer;
    }

    @JsonProperty
    public void setElasticsearchServer(String server) {
        this.elasticsearchServer = server;
    }
    
}
