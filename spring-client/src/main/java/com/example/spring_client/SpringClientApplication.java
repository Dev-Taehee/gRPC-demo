package com.example.spring_client;

import com.example.demo.grpc.HelloRequest;
import com.example.demo.grpc.HelloResponse;
import com.example.demo.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringClientApplication {

	public static void main(String[] args) {
        // 1️⃣ 서버 주소, 포트 지정
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        // 2️⃣ BlockingStub 생성
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        // 3️⃣ 요청 생성
        HelloRequest request = HelloRequest.newBuilder()
                .setName("태희")
                .build();

        // 4️⃣ gRPC 호출
        HelloResponse response = stub.sayHello(request);

        // 5️⃣ 응답 확인
        System.out.println(response.getMessage());

        // 6️⃣ 채널 종료
        channel.shutdown();
	}

}
