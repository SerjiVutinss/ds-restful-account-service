package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.LoginUser;
import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IPasswordClientService;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;
import ie.gmit.serji.restfulaccountservice.services.PasswordClientService;
import ie.gmit.serji.restfulaccountservice.services.UsersDbService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private IUsersDbService _usersService;
    private IPasswordClientService _passwordService;

    public LoginResource() {
        _usersService = UsersDbService.getInstance();
        _passwordService = new PasswordClientService();
    }

    @POST
    public Response loginUser(LoginUser loginUser) {
        // check to see if the user exists
        User u = _usersService.findByEmail(loginUser.getEmail());
        if (u != null) {
            System.out.println("Found User: " + u.getUserName());
            // user exists - check if password is valid

            System.out.println(loginUser.getPassword());


            boolean isValid = _passwordService.validateUser(u, loginUser.getPassword());

            // valid entry - return OK and the user
            if (isValid) return Response.ok(u).build();
        }

        // failed login - return unauthorized
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
