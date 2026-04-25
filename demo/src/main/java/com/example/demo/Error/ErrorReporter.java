package com.example.demo.Error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ErrorReporter {
    private final WebClient webClient;
    private final String endpoint;

    public ErrorReporter(
            WebClient webClient,
            @Value("${error-reporter.endpoint}") String endpoint
    ) {
        this.webClient = webClient;
        this.endpoint = endpoint;
    }

    @Async
    public void sendAsync(ErrorEvent event) {
        webClient.post()
                .uri(endpoint)
                .header("api_key","dc3da8d7-f85a-4415-87f8-8b8573550600")
                .bodyValue(event)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
