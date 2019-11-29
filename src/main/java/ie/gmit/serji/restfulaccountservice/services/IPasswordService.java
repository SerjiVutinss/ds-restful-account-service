package ie.gmit.serji.restfulaccountservice.services;

/**
 * Service interface intended to sit between a gRPC Client implementation and any Resource
 * which needs to execute methods on that client.
 *
 * Designed so that Resources never need to be aware of gRPC types,
 * e.g. HashInput, HashOutput, etc
 */
public interface IPasswordService {

    /**
     * Generate a hash and salt given a userId and password
     * @param userId - userId of the user whose password is to be hashed
     * @param password - the password to be hashed
     * @return a 2D byte array with 2 elements - 0 is hashedPassword bytes and 1 is salt bytes
     */
    byte[][] hashPassword(int userId, String password);

    /**
     * Checks whether the supplied password matches the supplied hashedPassword and salt
     * @param password
     * @param hashedPassword
     * @param salt
     * @return
     */
    boolean validatePassword(String password, byte[] hashedPassword, byte[] salt) throws Exception;

}
