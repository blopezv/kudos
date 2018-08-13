package com.resources.user.dao;

import com.api.UserData;

import java.util.List;

/**
 * Created by Brenda on 05/08/2018.
 */
public interface UserDao {
    boolean create(UserData userData);
    void delete(String userMongoID);
    List getAll(int page, int pageSize);
    Object getById(String userMongoID);
    void update(UserData userData);
}