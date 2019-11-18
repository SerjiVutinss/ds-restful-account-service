package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.UsersDbService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private IUsersDbService _usersService;
    public UsersResource() {
         _usersService = UsersDbService.getInstance();
    }

    @GET
    public Response getUsers() {

        List<User> allUsers  = _usersService.getAll();

        return Response.ok(allUsers).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Integer userId){

        User u = _usersService.getOne(userId);
        if(u!=null){
            return Response.ok(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createUser(User user) throws URISyntaxException {

        Integer newId = _usersService.insert(user);
        user.setUserId(newId);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

}
