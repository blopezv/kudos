package com.resources.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Brenda on 05/08/2018.
 */

@Path("rabbit")
@Produces(MediaType.APPLICATION_JSON)
public class RabbitResource {
    @GET
    @Path("suscribe")
    public Response suscribe() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://niewcprb:VpUcAVvXOZf7xxBLf-aTK_XORREfUHW_@lion.rmq.cloudamqp.com/niewcprb");
        factory.setConnectionTimeout(50000);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            final String QUEUE_NAME = "hello";
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}