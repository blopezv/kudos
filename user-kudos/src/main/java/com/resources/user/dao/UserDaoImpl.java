package com.resources.user.dao;

import com.api.UserData;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Brenda on 05/08/2018.
 */

import static com.mongodb.client.model.Filters.eq;

public class UserDaoImpl implements UserDao {

    private final MongoCollection<Document> collection;

    public UserDaoImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public long create(UserData userData) {
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        collection.insertOne(new Document(BasicDBObject.parse(json)));
        return 0;
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

    public Object getById(String userMongoID) {
        return null;
    }

    public List<UserData> getByNickName(String nickName) {
        return null;
    }

    public List<UserData> getByUserName(String userName) {
        return null;
    }
}