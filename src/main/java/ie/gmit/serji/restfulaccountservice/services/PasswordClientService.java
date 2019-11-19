package ie.gmit.serji.restfulaccountservice.services;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.ValidateInput;
import ie.gmit.serji.restfulaccountservice.GrpcPasswordServiceClient;
import ie.gmit.serji.restfulaccountservice.api.User;

public class PasswordClientService implements IPasswordClientService {

    private GrpcPasswordServiceClient _grpcPasswordService = GrpcPasswordServiceClient.getInstance();

    @Override
    public User hashPassword(User user) {

        HashInput input = HashInput.newBuilder()
                .setUserId(user.getUserId())
                .setPassword(user.getPassword())
                .build();

        HashOutput output = _grpcPasswordService.hash(input);

        System.out.println("HASH: " + output.getHashedPassword().toString());

        user.setHashedPassword(output.getHashedPassword().toByteArray());
        user.setSalt(output.getSalt().toByteArray());

        return user;
    }

    @Override
    public boolean validateUser(User user, String password) {

        ValidateInput input = ValidateInput.newBuilder()
                .setPassword(password)
                .setHashedPassword(ByteString.copyFrom(user.getHashedPassword()))
                .setSalt(ByteString.copyFrom(user.getSalt()))
                .build();

        return _grpcPasswordService.validate(input).getValue();
    }
}
