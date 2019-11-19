package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.UpsertUser;
import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private  IUsersDbService _usersDbService;

    public UsersResource(IUsersDbService usersService) {
        _usersDbService = usersService;
    }

    @GET
    public Response getUsers() {

        List<User> allUsers = _usersDbService.getAll();

        return Response.ok(allUsers).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Integer userId) {
        // try to get the object with the supplied ID from the service
        User u = _usersDbService.getOne(userId);
        if (u != null) {
            // object was found, return it along with OK
            return Response.ok(u).build();
        } else {
            // object not found by service, return NOT FOUND
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // TODO: check that request is well-formed
    @POST
    public Response createUser(@Valid UpsertUser user) {
        // Insert the new user to get a userId
        User u = new User(user);
        u = _usersDbService.insert(u, user.getPassword());

        return Response.status(Response.Status.CREATED).entity(u).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUserById(@PathParam("userId") Integer userId, UpsertUser user) {

        if (_usersDbService.getOne(userId) != null) {
            // user exists - create a new user object from the UpsertUser
            User updatedUser = new User(user);
            // update the user in the database, passing in the password so that
            // a new hash and salt can be generated
            updatedUser = _usersDbService.update(userId, updatedUser, user.getPassword());

            // return the updated user from the database
            return Response.ok(updatedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUserById(@PathParam("userId") Integer userId) {

        User u = _usersDbService.getOne(userId);
        if (u != null) {
            _usersDbService.remove(userId);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
