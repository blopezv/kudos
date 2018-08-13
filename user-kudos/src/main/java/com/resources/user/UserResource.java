package com.resources.user;

import com.api.UserData;
import com.codahale.metrics.annotation.Timed;
import com.resources.user.service.UserService;
import io.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Brenda on 05/08/2018.
 */
@Path(UserResource.NAME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    public static final String NAME = "users";

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(UserData userData) {
        try {
            boolean res = this.userService.create(userData);
            return Response.ok(res).build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.NO_CONTENT_204).build();
        }
    }

    @GET
    @UnitOfWork
    public Response getAll() {
        return Response.ok(userService.getAll(1, 25)).build();
    }

    @GET
    @Path("{id}")
    @Timed
    public Response getById(@PathParam("id") String userId) {
        Object object = userService.getById(userId);
        if (object != null) {
            return Response.ok(userService.getById(userId)).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("{id}")
    @Timed
    public Response delete(@PathParam("id") String userId) {
        userService.delete(userId);
        return Response.ok().build();
    }
}