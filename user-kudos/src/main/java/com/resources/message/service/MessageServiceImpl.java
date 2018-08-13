package com.resources.message.service;

import com.api.UserData;
import com.core.RabbitMessage;
import com.resources.user.dao.UserDao;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
/**
 * Created by Brenda on 05/08/2018.
 */
public class MessageServiceImpl implements MessageService {

    private final UserDao userDao;

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
        //TODO: aqui haces tu update ok
        //userDao.update(userData);
    }
}