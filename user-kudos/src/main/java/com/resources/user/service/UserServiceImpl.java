package com.resources.user.service;

import com.api.UserData;
import com.resources.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public boolean create(UserData userData) {
        String customLog = logCustomized("New user", userData.getNickName());
        LOG.info(customLog);
        return this.userDao.create(userData);
    }

    @Override
    public void delete(String userMongoID) {
        String customLog = logCustomized("Delete user _id", userMongoID);
        LOG.info(customLog);
        this.userDao.delete(userMongoID);
    }

    @Override
    public List<UserData> getAll(int page, int pageSize) {
        String customLog = logCustomized("Get All", " users");
        LOG.info(customLog);
        return this.userDao.getAll(page, pageSize);
    }

    @Override
    public Object getById(String userId) {
        String customLog = logCustomized("Delete user _id", userId);
        LOG.info(customLog);
        return userDao.getById(userId);
    }

    public String logCustomized(String event, String detail) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return String.format("[%s] [%s] %s", dateFormat.format(date), event, detail);
    }
}