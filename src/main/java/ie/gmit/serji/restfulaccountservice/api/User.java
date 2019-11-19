package ie.gmit.serji.restfulaccountservice.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

    //    userId:
    //    type: integer
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
    private byte[] hashedPassword;

    //    salt:
    //    type: string
    private byte[] salt;

    public User() {
    }

    public User(
            Integer userId,
            String userName,
            String email,
            String password,
            byte[] hashedPassword,
            byte[] salt
            ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
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

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }


}
