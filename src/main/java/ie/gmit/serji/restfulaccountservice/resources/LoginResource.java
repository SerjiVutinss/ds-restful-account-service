package ie.gmit.serji.restfulaccountservice.resources;

import ie.gmit.serji.restfulaccountservice.api.LoginUser;
import ie.gmit.serji.restfulaccountservice.api.User;
import ie.gmit.serji.restfulaccountservice.services.IPasswordService;
import ie.gmit.serji.restfulaccountservice.services.IUsersDbService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private  IUsersDbService _usersService;
    private  IPasswordService _passwordService;

    @Inject
    public LoginResource(IUsersDbService usersService, IPasswordService passwordService) {
        _usersService = usersService;
        _passwordService = passwordService;
    }

    @POST
    public Response loginUser(@Valid LoginUser loginUser) {
        // check to see if the user exists
        User user = _usersService.findByEmail(loginUser.getEmail());
        if (user != null) {
            // user exists - check if password is valid by calling IPasswordService implementation
            boolean isValid = _passwordService.validatePassword(loginUser.getPassword(), user.getHashedPassword(), user.getSalt());
            // valid password - return OK and the User
            if (isValid) return Response.ok(user).build();
        }
        // failed login - return unauthorized
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
