package com.dpaula.azuremessage.endpoint;

import com.dpaula.azuremessage.dto.PagamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/pagamentos")
@CrossOrigin(origins = "*")
@Tag(name = "Clientes", description = "Serviços para gerenciar pagamentos")
public interface IPagamentoController {

    @Operation(summary = "Efetuar Pagamento", description = "Post para realizar um pagamento")
    @PostMapping
    ResponseEntity<PagamentoDTO> post(@NotNull @Valid @RequestBody final PagamentoDTO clienteInput);

}
