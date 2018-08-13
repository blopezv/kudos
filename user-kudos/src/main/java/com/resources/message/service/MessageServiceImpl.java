package com.resources.message.service;

import com.api.UserData;
import com.core.RabbitMessage;
import com.resources.user.dao.UserDao;
import com.resources.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Brenda on 05/08/2018.
 */
public class MessageServiceImpl implements MessageService {

    private final UserDao userDao;
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    public MessageServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void kudosCreated(RabbitMessage rabbitMessage) throws Throwable {
        Object objUser = userDao.getById(rabbitMessage.getBodyMessage().getId());
        if (objUser == null ) {
            throw new NotFoundException("users");
        }
        UserData userData = (UserData)objUser ;
        userData.setQuantityKudo(userData.getQuantityKudo() + rabbitMessage.getBodyMessage().getKudosQty());

        String customLog = logCustomized("Update user quantityKudos", userData.getNickName());
        LOG.info(customLog);
        userDao.update(userData);
    }

    public String logCustomized(String event, String detail) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return String.format("[%s] [%s] %s", dateFormat.format(date), event, detail);
    }
}