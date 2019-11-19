package ie.gmit.serji.restfulaccountservice.resources;

import com.google.protobuf.Int32Value;
import ie.gmit.serji.restfulaccountservice.GrpcPasswordServiceClient;
import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IPasswordClientService;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.PasswordClientService;
import ie.gmit.serji.restfulaccountservice.services.UsersDbService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private IUsersDbService _usersService;
    private IPasswordClientService _passwordService;

    public UsersResource() {
        _usersService = UsersDbService.getInstance();
        _passwordService = new PasswordClientService();
    }

    @GET
    public Response getUsers() {

        List<User> allUsers = _usersService.getAll();

        return Response.ok(allUsers).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Integer userId) {

        User u = _usersService.getOne(userId);
        if (u != null) {
            return Response.ok(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // TODO: check that request is well-formed
    @POST
    public Response createUser(User user) {
        // Insert the new user to get a userId
        Integer newId = _usersService.insert(user);

        // TODO: async call to password service generateHash() here!
        user.setUserId(newId);
        user = _passwordService.hashPassword(user);

        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUserById(@PathParam("userId") Integer userId, User user) {

        if (_usersService.getOne(userId) != null) {
            // user exists - update the user in the database
            User updatedUser = _usersService.update(user.getUserId(), user);
            // return the updated user from the database
            return Response.ok(updatedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUserById(@PathParam("userId") Integer userId) {

        User u = _usersService.getOne(userId);
        if (u != null) {
            _usersService.remove(userId);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
