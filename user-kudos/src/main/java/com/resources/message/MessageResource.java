package com.resources.message;

import com.core.RabbitMessage;
import com.resources.message.service.MessageService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Created by Brenda on 05/08/2018.
 */
@Path(MessageResource.NAME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
    public static final String NAME = "kudos_message";
    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(RabbitMessage rabbitMessage) throws Throwable {
        messageService.kudosCreated(rabbitMessage);
        return Response.ok().build();
    }
}
