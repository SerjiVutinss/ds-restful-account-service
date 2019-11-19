package ie.gmit.serji.restfulaccountservice.api;

import javax.validation.constraints.Pattern;

public class LoginUser {

    //    email:
    //    type: string
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
