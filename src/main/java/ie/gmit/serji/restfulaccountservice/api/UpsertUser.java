package ie.gmit.serji.restfulaccountservice.api;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class UpsertUser {

    private Integer userId;
    @NotEmpty
    private String userName;
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;
    @NotEmpty
    private String password;

    public UpsertUser() {
    }

    public UpsertUser(
            Integer userId,
            String userName,
            String email,
            String password
    ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
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

}
