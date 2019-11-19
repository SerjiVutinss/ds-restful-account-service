package ie.gmit.serji.restfulaccountservice.services;

public interface IPasswordService {

//    byte[][] hashPassword(int userId, String password);
    byte[][] hashPassword(int userId, String password);

    boolean validatePassword(String password, byte[] hashedPassword, byte[] salt);

}
