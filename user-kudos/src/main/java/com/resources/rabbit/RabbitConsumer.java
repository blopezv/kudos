package com.resources.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * Created by Brenda on 05/08/2018.
 */
public class RabbitConsumer extends RabbitConnection implements Runnable, Consumer {
    public RabbitConsumer(String endpointName) throws IOException, TimeoutException {
        super(endpointName);
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

    }
}