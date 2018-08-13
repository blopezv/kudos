package com.resources.rabbit;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;
/**
 * Created by Brenda on 05/08/2018.
 */
public class RabbitProducer extends RabbitConnection {

    public RabbitProducer(String endpointName) throws IOException, TimeoutException {
        super(endpointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}