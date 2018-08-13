package com.resources.message.service;

import com.core.RabbitMessage;
/**
 * Created by Brenda on 05/08/2018.
 */
public interface MessageService {
    void kudosCreated(RabbitMessage rabbitMessage) throws Throwable;
}