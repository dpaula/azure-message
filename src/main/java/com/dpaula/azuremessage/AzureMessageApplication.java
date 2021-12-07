package com.dpaula.azuremessage;

import com.azure.spring.integration.core.api.reactor.Checkpointer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import static com.azure.spring.integration.core.AzureHeaders.CHECKPOINTER;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class AzureMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureMessageApplication.class, args);
    }

    @Bean
    public Consumer<Message<String>> consume() {
        return message -> {
            Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);
            log.info("New message received: '{}'", message.getPayload());
            checkpointer.success()
                .doOnSuccess(success -> log.info("Message '{}' successfully checkpointed", message.getPayload()))
                .doOnError(error -> log.error("Exception found", error))
                .subscribe();
        };
    }

}
