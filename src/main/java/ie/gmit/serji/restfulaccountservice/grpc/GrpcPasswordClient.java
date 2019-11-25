package ie.gmit.serji.restfulaccountservice.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.BoolValue;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.PasswordServiceGrpc;
import ie.gmit.serji.passwordservice.ValidateInput;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 * See IGrpcPasswordClient for details of implemented methods
 */
public class GrpcPasswordClient implements IGrpcPasswordClient {

    // Host the gRPC service is running on.
    private static String HOST = "localhost";
    // Port the gRPC service is running on.
    private static int PORT = 8080;

    public static void configure(String grpcHost, int grpcPort) {
        HOST = grpcHost;
        PORT = grpcPort;
    }


    // Client setup.
    private final ManagedChannel channel;
    private final PasswordServiceGrpc.PasswordServiceFutureStub futureStub;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub blockingStub;

    // Singleton Pattern
    private static GrpcPasswordClient _instance;

    public static GrpcPasswordClient getInstance() {
        if (_instance == null) _instance = new GrpcPasswordClient();
        return _instance;
    }
    // End Singleton Pattern

    private GrpcPasswordClient() {

        channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext()
                .build();

        // Stub to use for synchronous, blocking calls.
        blockingStub = PasswordServiceGrpc.newBlockingStub(channel);
        // Stub to use for asynchronous calls.
        futureStub = PasswordServiceGrpc.newFutureStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // IGrpdPasswordClient implementation.
    @Override
    public BoolValue validate(ValidateInput validateInput) {
        // Simply call the gRPC method synchronously and return the response.
        return blockingStub.validate(validateInput);
    }

    @Override
    public ListenableFuture<HashOutput> hashAsync(HashInput hashInput) {

        StreamObserver<HashOutput> responseObserver = new StreamObserver<HashOutput>() {
            @Override
            public void onNext(HashOutput output) {
                System.out.println("Received HashOutput: " + output);
            }

            @Override
            public void onError(Throwable throwable) {
                Status status = Status.fromThrowable(throwable);
                System.out.println("RPC Error: {0} " + status);
            }

            @Override
            public void onCompleted() {
                System.out.println("Finished receiving HashOutput");
            }
        };

        try {
            System.out.println("INFO " + "Requesting Hashed password and Salt from gRPC Server");
            // Get the HashOutput response as a ListenableFuture.
            ListenableFuture<HashOutput> output = futureStub.hash(hashInput);
            // Return the ListenableFuture - this will be consumed by the IPasswordService implementation.
            return output;

        } catch (
                StatusRuntimeException ex) {
            System.out.println("RPC failed: {0} " + ex.getStatus());
            // Return null because an error has occurred.
            return null;
        }
    }
    // End IGrpdPasswordClient implementation.

    // Synchronous call to hash() - unused
    @Override
    public HashOutput hash(HashInput hashInput) {
        return blockingStub.hash(hashInput);
    }
}
