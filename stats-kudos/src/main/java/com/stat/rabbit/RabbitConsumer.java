package com.stat.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.stat.core.RabbitMessage;
import com.stat.resources.message.MessageService;
import com.stat.resources.message.MessageServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Brenda on 07/08/2018.
 */
public class RabbitConsumer extends RabbitConnection implements Runnable, Consumer {
    private final MessageService messageService;
    public RabbitConsumer(String endpointName) throws IOException, TimeoutException {
        super(endpointName);
        messageService = new MessageServiceImpl();
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String s) {
        System.out.println("NOT IMPLEMENTED: handleConsumeOk");
    }

    @Override
    public void handleCancelOk(String s) {
        System.out.println("NOT IMPLEMENTED: handleCancelOk");
    }

    @Override
    public void handleCancel(String s) throws IOException {
        System.out.println("NOT IMPLEMENTED: handleCancel");
    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {
        System.out.println("NOT IMPLEMENTED: handleShutdownSignal");
    }

    @Override
    public void handleRecoverOk(String s) {
        System.out.println("NOT IMPLEMENTED: handleRecoverOk");
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String content = new String(body, "UTF-8");
        RabbitMessage user = mapper.readValue(content, RabbitMessage.class);

        try {
            messageService.sendNewKudos(user);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}