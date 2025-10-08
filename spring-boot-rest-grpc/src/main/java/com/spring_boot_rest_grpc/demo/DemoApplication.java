package com.spring_boot_rest_grpc.demo;

import com.spring_boot_rest_grpc.demo.grpc.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    private Server grpcServer;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @PostConstruct
    public void startGrpcServer() throws IOException {
        grpcServer = NettyServerBuilder.forPort(9090)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        System.out.println("✅ gRPC Server started on port 9090 (Netty)");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("🛑 Shutting down gRPC server...");
            grpcServer.shutdown();
        }));
    }

}
