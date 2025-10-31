package com.gborkar.spring_aggregator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import com.gborkar.spring_aggregator.service.UserPostAgregatorService;

@SpringBootApplication
public class SpringAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAggregatorApplication.class, args);
    }
    
    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    @Bean
    CommandLineRunner run(UserPostAgregatorService userPostAgrregatorService) {
        return args -> {
            System.out.println(
                    "User Posts: " + userPostAgrregatorService.retrieveUserAndPostByUserIdByCompletableFuture(1));
        };
    }
    
    @Bean(destroyMethod = "shutdown")
    ExecutorService executorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

}
