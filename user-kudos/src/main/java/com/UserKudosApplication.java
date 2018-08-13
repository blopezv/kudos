package com;

import com.modules.MongoManaged;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.resources.user.UserResource;
import com.resources.user.dao.UserDao;
import com.resources.user.dao.UserDaoImpl;
import com.resources.message.MessageResource;
import com.resources.message.service.MessageService;
import com.resources.message.service.MessageServiceImpl;
import com.resources.user.service.UserService;
import com.resources.user.service.UserServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by Brenda on 05/08/2018.
 */
public class UserKudosApplication extends Application<UserKudosConfiguration> {
    public static void main(final String[] args) throws Exception {
        new UserKudosApplication().run(args);
    }

    @Override
    public void run(final UserKudosConfiguration configuration,
                    final Environment environment) {

        MongoCredential credential = MongoCredential.createCredential(configuration.getMongoUser(), configuration.getMongoDB(), configuration.getMongoPass().toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(configuration.getMongoHost(), configuration.getMongoPort()), Arrays.asList(credential));
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);
        MongoDatabase db = mongoClient.getDatabase(configuration.getMongoDB());
        MongoCollection<Document> collection = db.getCollection(configuration.getCollectionName());

        UserDao userDao = new UserDaoImpl(collection);
        UserService userService = new UserServiceImpl(userDao);
        UserResource userResource = new UserResource(userService);

        MessageService messageService = new MessageServiceImpl(userDao);
        MessageResource messageResource = new MessageResource(messageService);

        environment.jersey().register(userResource);
        environment.jersey().register(messageResource);
    }
}