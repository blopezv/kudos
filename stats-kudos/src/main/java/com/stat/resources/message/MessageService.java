package com.stat.resources.message;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.stat.core.RabbitMessage;

/**
 * Created by Brenda on 07/08/2018.
 */
public interface MessageService {
    void sendNewKudos(RabbitMessage rabbitMessage) throws UnirestException;
}