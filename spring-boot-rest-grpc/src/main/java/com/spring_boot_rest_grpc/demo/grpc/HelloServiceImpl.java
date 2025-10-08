package com.spring_boot_rest_grpc.demo.grpc;

import com.example.demo.grpc.HelloRequest;
import com.example.demo.grpc.HelloResponse;
import com.example.demo.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String message = "Hello " + request.getName() + " from gRPC (Netty)";
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
