package com.resources.user.service;

import com.api.UserData;
import com.resources.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by brenda on 12/08/2018.
 */
public class UserServiceImpl implements UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public long create(UserData userData) {
        return this.userDao.create(userData);
    }
    @Override
    public void delete(String userMongoID) {
        this.userDao.delete(userMongoID);
    }

    @Override
    public List<UserData> getAll(int page, int pageSize) {
        return this.userDao.getAll(page, pageSize);
    }
}