package com.dpaula.azuremessage.conf;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

/**
 * @author Fernando de Lima on 02/12/21
 */
@Slf4j
@Configuration
public class ServiceProducerConfiguration {

    @Bean
    public Sinks.Many<Message<String>> many() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply(Sinks.Many<Message<String>> many) {
        return () -> many.asFlux()
            .doOnNext(m -> log.info("Manually sending message {}", m))
            .doOnError(t -> log.error("Error encountered", t));
    }
}
