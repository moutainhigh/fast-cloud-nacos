package fast.cloud.nacos.grpc.example.service.client;

import fast.cloud.nacos.grpc.example.grpc.GrpcTestServiceGrpc;
import fast.cloud.nacos.grpc.example.grpc.GrpcTestService_Request_String;
import fast.cloud.nacos.grpc.example.grpc.GrpcTestService_Response_String;
import fast.cloud.nacos.grpc.example.service.GrpcTestServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloService {
    @Value("${grpc.port}")
    private int port;

    @Autowired
    ManagedChannel managedChannel;

    private  GrpcTestServiceGrpc.GrpcTestServiceBlockingStub blockingStub;

    public void hello() {
        GrpcTestService_Response_String grpcTestService_response_string = blockingStub.reqString(GrpcTestService_Request_String
                .newBuilder()
                .setName("BBB")
                .build());

        System.out.println(grpcTestService_response_string+"port:"+ port);
    }


    @PostConstruct
    private void initializeClient() {
        blockingStub = GrpcTestServiceGrpc.newBlockingStub(managedChannel);
    }
}