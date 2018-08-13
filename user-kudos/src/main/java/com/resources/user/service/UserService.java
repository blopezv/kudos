package com.resources.user.service;

import com.api.UserData;

import java.util.List;

/**
 * Created by Brenda on 05/08/2018.
 */
public interface UserService {
    long create(UserData userData);
    void delete(String userMongoID);
    List<UserData> getAll(int page, int pageSize);
}