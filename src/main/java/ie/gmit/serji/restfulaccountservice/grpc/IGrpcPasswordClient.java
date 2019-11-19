package ie.gmit.serji.restfulaccountservice.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.BoolValue;
import ie.gmit.serji.passwordservice.HashInput;
import ie.gmit.serji.passwordservice.HashOutput;
import ie.gmit.serji.passwordservice.ValidateInput;

/**
 *  Service interface specifying expected behaviour of a gRPC password service client.
 *
 * Specification for a GrpcPasswordClient
 */
public interface IGrpcPasswordClient {

    /**
     * Validate the validateInput object by checking whether its password property
     * matches with the hashedPassword and salt properties.
     *
     * This operation should be performed synchronously.
     *
     * @param validateInput object with properties to be checked
     * @return true if properties match
     */
    BoolValue validate(ValidateInput validateInput);

    /**
     * Hashes the password property on the input object and returns a future of HashOutput
     * containing the hashedPassword and salt.
     *
     * This operation should be performed asynchronously.
     *
     * @param hashInput object with password property to be hashed
     * @return ListenableFuture<HashOutput> which will contain the hashedPassword and salt.
     */
    ListenableFuture<HashOutput> hashAsync(HashInput hashInput);

    /**
     * Hashes the password property on the input object and returns a future of HashOutput
     * containing the hashedPassword and salt.
     *
     * This operation should be performed synchronously.
     *
     * @param hashInput object with password property to be hashed
     * @return ListenableFuture<HashOutput> which will contain the hashedPassword and salt.
     */
    @Deprecated
    HashOutput hash(HashInput hashInput);
}
