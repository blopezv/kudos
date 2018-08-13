package com.resources.user;

import com.api.UserData;
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
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(UserData userData) {
        try {
            long id = this.userService.create(userData);
            return Response.ok(id).build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.NO_CONTENT_204).build();
        }
    }

    @GET
    @UnitOfWork
    public Response getAll() {
        return Response.ok(userService.getAll(1, 25)).build();
    }
}