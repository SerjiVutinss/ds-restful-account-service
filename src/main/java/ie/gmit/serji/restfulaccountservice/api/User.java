package ie.gmit.serji.restfulaccountservice.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

    //    userId:
    //    type: integer
    @NotNull
    private Integer userId;

    //    userName:
    //    type: string
    private String userName;

    //    email:
    //    type: string
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    //    password:
    //    type: string
    private String password;

    //    hashedPassword:
    //    type: string
    private String hashedPassword;

    //    salt:
    //    type: string
    private String salt;

    public User() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


}
