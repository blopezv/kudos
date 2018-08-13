package com.resources.user.dao;

import com.api.UserData;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Brenda on 05/08/2018.
 */

import static com.mongodb.client.model.Filters.eq;

public class UserDaoImpl implements UserDao {

    private final MongoCollection<Document> collection;

    public UserDaoImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public boolean create(UserData userData) {
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        collection.insertOne(new Document(BasicDBObject.parse(json)));
        return true;
    }

    public void delete(String userMongoID) {
        collection.deleteOne(eq("_id", userMongoID));
    }

    public List getAll(int page, int pageSize) {
        return collection.find().map((Document value) ->
                new UserData(value.get("_id").toString(),
                         value.get("nickName").toString(),
                        value.get("userName").toString(),
                        Integer.valueOf(value.get("quantityKudo").toString()))).into(new ArrayList<>());
    }

    public Optional getById(String userMongoID) {
        return Optional.ofNullable(collection.find(eq("_id", new ObjectId(userMongoID))).map(value ->
                new UserData(value.get("_id").toString(), value.get("nickName").toString(),
                value.get("userName").toString(), Integer.valueOf(value.get("quantityKudo").toString()))).first());
    }

    @Override
    public void update(UserData userData) {
        Bson newValue = new Document("quantityKudo", userData.getQuantityKudo());
        Bson updateOperationDocument = new Document("$set", newValue);
        collection.updateOne(eq("_id", new ObjectId(userData.getUserMongoId())), updateOperationDocument);
    }
}