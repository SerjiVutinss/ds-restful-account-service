package ie.gmit.serji.restfulaccountservice;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Int32Value;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.PasswordServiceGrpc;
import ie.gmit.serji.passwordservice.ValidateInput;
import ie.gmit.serji.restfulaccountservice.api.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcPasswordServiceClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private static final Logger logger = Logger.getLogger(GrpcPasswordServiceClient.class.getName());

    private final ManagedChannel channel;
    private final PasswordServiceGrpc.PasswordServiceStub asyncPasswordService;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub syncPasswordService;


    private static User _theUser;

    private static GrpcPasswordServiceClient _instance;

    public static GrpcPasswordServiceClient getInstance() {
        if (_instance == null) _instance = new GrpcPasswordServiceClient();
        return _instance;
    }

    private GrpcPasswordServiceClient() {

        channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        syncPasswordService = PasswordServiceGrpc.newBlockingStub(channel);
        asyncPasswordService = PasswordServiceGrpc.newStub(channel);
    }

    public HashOutput hash(HashInput hashInput) {

        return syncPasswordService.hash(hashInput);
    }

    public BoolValue validate(ValidateInput validateInput){
        return syncPasswordService.validate(validateInput);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

//    // asynchronous call to hash()
//    public HashOutput hash( HashInput hashInput) {
//        final HashOutput[] retVal = new HashOutput[1];
//        StreamObserver responseObserver = new StreamObserver<HashOutput>() {
//            @Override
//            public void onNext(HashOutput output) {
//                logger.info("Received hash: " + output);
//                retVal[0] = output;
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Status status = Status.fromThrowable(throwable);
//                logger.log(Level.WARNING, "RPC Error: {0}", status);
//            }
//
//            @Override
//            public void onCompleted() {
//                logger.info("Finished receiving HashOutput");
//            }
//        };
//
////        try {
//            logger.info("Requesting HashOutput");
//            asyncPasswordService.hash(hashInput, responseObserver);
////            logger.info("Returned from requesting HashOutput");
////        } catch (
////                StatusRuntimeException ex) {
////            logger.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
////            return null;
////        }
//        return retVal[0];
//    }

//    // async call to validate()
//    public void validate(ValidateInput input) {
//        StreamObserver<BoolValue> responseObserver = new StreamObserver<BoolValue>() {
//            @Override
//            public void onNext(BoolValue output) {
//                logger.info("Received BoolValue: " + output.getValue());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Status status = Status.fromThrowable(throwable);
//                logger.log(Level.WARNING, "RPC Error: {0}", status);
//            }
//
//            @Override
//            public void onCompleted() {
//                logger.info("Finished receiving BoolValue");
//                // End program
//                System.exit(0);
//            }
//        };
//
//        try {
//            logger.info("Requesting BoolValue");
//            asyncPasswordService.validate(input, responseObserver);
//            logger.info("Returned from requesting BoolValue");
//        } catch (
//                StatusRuntimeException ex) {
//            logger.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
//            return;
//        }
//    }

    // async call to generatePassword()
    public void generatePassword(Int32Value userId) {
        StreamObserver<HashInput> responseObserver = new StreamObserver<HashInput>() {

            @Override
            public void onNext(HashInput value) {
//                 access to userId is possible here but not needed right now
//                _theUser.userId = value.getUserId();
                logger.info("Got HashInput with password: " + value.getPassword() + " from generatePassword()");
//                _theUser.password = value.getPassword();
            }

            @Override
            public void onError(Throwable throwable) {
                Status status = Status.fromThrowable(throwable);
                logger.log(Level.WARNING, "RPC Error: {0}", status);
            }

            @Override
            public void onCompleted() {
                logger.info("Finished receiving HashInput from generatePassword()");
//                HashInput input = HashInput.newBuilder()
//                        .setUserId(_theUser.userId)
//                        .setPassword(_theUser.password)
//                        .build();
//                hash(input);
            }
        };

        try {
            logger.info("Requesting Password");
            asyncPasswordService.generatePassword(userId, responseObserver);
            logger.info("Returned from requesting Password");
        } catch (
                StatusRuntimeException ex) {
            logger.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
            return;
        }
    }


}
