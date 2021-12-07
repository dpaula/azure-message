package com.dpaula.azuremessage.service;

import com.dpaula.azuremessage.dto.PagamentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PagamentoService {

    public static final ZoneId ZONA_ID = ZoneId.of("America/Sao_Paulo");

    private final StreamBridge streamBridge;

    public PagamentoDTO efetuarPagamento(final PagamentoDTO clienteInput) {

        try {
            new ObjectMapper().writeValueAsString(clienteInput);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("Going to add message {} to Sinks.Many.", clienteInput);
        streamBridge.send("topico-unidocs-teste-fernando", toJsonStr(clienteInput));
        log.info("Sent!!!!");

        clienteInput.setDataPagamento(LocalDateTime.now(ZONA_ID));

        return clienteInput;
    }

    private static String toJsonStr(final PagamentoDTO clienteInput) {

        try {
            final var configure = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            configure.registerModule(new JavaTimeModule());
            return configure.writeValueAsString(clienteInput);
        } catch (final JsonProcessingException e) {
            log.error("Error parsing JSON, {}", clienteInput, e);
            return "Error";
        }
    }
}
