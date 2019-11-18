package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.UsersDbService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private IUsersDbService _usersService = UsersDbService.getInstance();

    @GET
    public Response getUsers() {

        List<User> allUsers  = _usersService.getAll();

        return Response.ok(allUsers).build();
    }

}
