package com.stat.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Brenda on 07/08/2018.
 */
public abstract class RabbitConnection {
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public RabbitConnection(String endpointName) throws IOException, TimeoutException {
        this.endPointName = endpointName;

        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://niewcprb:VpUcAVvXOZf7xxBLf-aTK_XORREfUHW_@lion.rmq.cloudamqp.com/niewcprb");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(endpointName, false, false, false, null);
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}