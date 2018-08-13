package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Brenda on 05/08/2018.
 */
public class UserKudosConfiguration extends Configuration {
    @JsonProperty
    @NotEmpty
    public String mongoHost;

    @JsonProperty
    public int mongoPort;

    @JsonProperty
    @NotEmpty
    public String mongoDB;

    @JsonProperty
    @NotEmpty
    public String mongoUser;

    @JsonProperty
    @NotEmpty
    public String mongoPass;

    @JsonProperty
    @NotEmpty

    public String collectionName;

    public String getMongoHost() {
        return mongoHost;
    }

    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(String mongoDB) {
        this.mongoDB = mongoDB;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getMongoUser() {
        return mongoUser;
    }

    public void setMongoUser(String mongoUser) {
        this.mongoUser = mongoUser;
    }

    public String getMongoPass() {
        return mongoPass;
    }

    public void setMongoPass(String mongoPass) {
        this.mongoPass = mongoPass;
    }
}