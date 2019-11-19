package ie.gmit.serji.restfulaccountservice.services;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.ValidateInput;
import ie.gmit.serji.restfulaccountservice.GrpcPasswordServiceClient;

import java.util.concurrent.ExecutionException;

public class PasswordService implements IPasswordService {

    private final GrpcPasswordServiceClient _grpcPasswordService;

    public PasswordService(GrpcPasswordServiceClient grpcPasswordServiceClient) {
        this._grpcPasswordService = grpcPasswordServiceClient;
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

    @Override
    public boolean validatePassword(String password, byte[] hashedPassword, byte[] salt) {

        ValidateInput input = ValidateInput.newBuilder()
                .setPassword(password)
                .setHashedPassword(ByteString.copyFrom(hashedPassword))
                .setSalt(ByteString.copyFrom(salt))
                .build();

        return _grpcPasswordService.validate(input).getValue();
    }

    // Asynchronous Call to GrpcPasswordServiceClient
    @Override
    public byte[][] hashPassword(int userId, String password) {

        byte[][] result = new byte[2][];


        HashInput hashInput = HashInput.newBuilder()
                .setUserId(userId)
                .setPassword(password)
                .build();

        ListenableFuture<HashOutput> future = _grpcPasswordService.hashAsync(hashInput);

        try {
            HashOutput response = future.get();
            result[0] = response.getHashedPassword().toByteArray();
            result[1] = response.getSalt().toByteArray();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
