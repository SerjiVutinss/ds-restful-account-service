package ie.gmit.serji.restfulaccountservice.api;

import javax.validation.constraints.Pattern;

public class User {

    private Integer userId;
    private String userName;
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;
    private byte[] hashedPassword;
    private byte[] salt;

    public User() {
    }

    public User(UpsertUser u) {
        this.userId = u.getUserId();
        this.userName = u.getUserName();
        this.email = u.getEmail();
    }

    public User(
            Integer userId,
            String userName,
            String email
    ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
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
