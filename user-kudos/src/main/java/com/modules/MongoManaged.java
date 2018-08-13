package com.modules;

import com.mongodb.Mongo;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by Brenda on 05/08/2018.
 */
public class MongoManaged implements Managed {
    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }
}