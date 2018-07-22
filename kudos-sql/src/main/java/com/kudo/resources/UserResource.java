package com.kudo.resources;

import com.kudo.core.User;
import com.kudo.dao.UserDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by brenda on 22/07/2018.
 */
@Path("/user")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    private final UserDao userDao;

    @Inject
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    public Response getUserList() {
        return Response.status(Response.Status.OK).entity(this.userDao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(this.userDao.findById(id)).build();
    }

    @POST
    @UnitOfWork
    public Response add(@Valid User user) {
        try {
            User newPerson = this.userDao.insert(user);
            return Response.ok(newPerson).build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.NO_CONTENT_204).build();
        }
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Response update(@Valid User user) {
        try {
            this.userDao.update(user);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.NO_CONTENT_204).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") Integer id) {
        try {
            this.userDao.delete(this.userDao.findById(id));
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.NO_CONTENT_204).build();
        }
    }
}