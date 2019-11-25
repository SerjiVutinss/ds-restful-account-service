package ie.gmit.serji.restfulaccountservice.services;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.ValidateInput;
import ie.gmit.serji.restfulaccountservice.grpc.GrpcPasswordClient;
import ie.gmit.serji.restfulaccountservice.grpc.IGrpcPasswordClient;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

/**
 * Implementation of IPasswordService
 * <p>
 * See IPasswordService for details of implemented methods
 */
public class GrpcPasswordService implements IPasswordService {

    /**
     *
     */
    private final IGrpcPasswordClient _grpcPasswordClient;

    @Inject
    public GrpcPasswordService() {
        // Use the GrpcPasswordClient singleton
        _grpcPasswordClient = GrpcPasswordClient.getInstance();
    }

//    public PasswordService(IGrpcPasswordClient grpcPasswordClient) {
//        this._grpcPasswordClient = grpcPasswordClient;
//    }

    @Override
    public boolean validatePassword(String password, byte[] hashedPassword, byte[] salt) {

        ValidateInput input = ValidateInput.newBuilder()
                .setPassword(password)
                .setHashedPassword(ByteString.copyFrom(hashedPassword))
                .setSalt(ByteString.copyFrom(salt))
                .build();

        return _grpcPasswordClient.validate(input).getValue();
    }

    /*
    Asynchronous Call to GrpcPasswordServiceClient
    Note that this method is called synchronously in the IPasswordService implementation
     */
    @Override
    public byte[][] hashPassword(int userId, String password) {

        // Create the request object using method arguments.
        HashInput hashInput = HashInput.newBuilder()
                .setUserId(userId)
                .setPassword(password)
                .build();

        // Call the gRPC client method and store its response as a ListenableFuture.
        ListenableFuture<HashOutput> future = _grpcPasswordClient.hashAsync(hashInput);
        // Create a return value for this method.
        byte[][] result = new byte[2][];
        try {
            // Block until the response is received.
            HashOutput response = future.get();
            System.out.println("Received Hashed Password and Salt from Server");
            // Set the values in the return value.
            result[0] = response.getHashedPassword().toByteArray();
            result[1] = response.getSalt().toByteArray();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // Return the values.
        return result;
    }

//    // Synchronous Calls
//    @Override
//    public byte[][] hashPassword(int userId, String password) {
//
//        HashInput input = HashInput.newBuilder()
//                .setUserId(userId)
//                .setPassword(password)
//                .build();
//
//        HashOutput hashOutput = _grpcPasswordService.hash(input);
//
//        byte[][] result = new byte[2][];
//        result[0] = hashOutput.getHashedPassword().toByteArray();
//        result[1] = hashOutput.getSalt().toByteArray();
//
//        return result;
//    }
}
