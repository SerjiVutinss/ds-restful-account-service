package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.User;

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

    private List<User> usersList;

    @GET
    public Response getUsers() {

        usersList = new ArrayList();

        User u1 = new User();
        u1.setUserId(1);;
        u1.setUserName("JohnDoe");;
        u1.setEmail("johndoe@example.com");
        u1.setPassword("Hello123!");

        usersList.add(u1);

        return Response.ok(usersList).build();
    }

}
