package com.dpaula.azuremessage.endpoint.impl;

import com.dpaula.azuremessage.dto.PagamentoDTO;
import com.dpaula.azuremessage.endpoint.IPagamentoController;
import com.dpaula.azuremessage.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PagamentoController implements IPagamentoController {

    private final PagamentoService service;


    @Override
    public ResponseEntity<PagamentoDTO> post(final PagamentoDTO clienteInput) {
        final var pagamento = service.efetuarPagamento(clienteInput);

        return ResponseEntity.ok(pagamento);
    }
}
