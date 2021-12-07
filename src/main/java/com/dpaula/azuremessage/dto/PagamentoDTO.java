package com.dpaula.azuremessage.dto;

import com.dpaula.azuremessage.enuns.EnTipoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_WRITE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class PagamentoDTO implements Serializable {

    private static final long serialVersionUID = 8926274467059821529L;
    @Schema(accessMode = READ_WRITE, example = "6a0f69fc-63c3-48e9-9f0a-efde73604d69", description = "Id do Cliente")
    private UUID id;

    @Schema(accessMode = READ_WRITE, example = "Fernando de Lima", description = "Nome Completo do Cliente")
    @NotEmpty
    @Size(max = 200)
    private String nomeCliente;

    @Schema(accessMode = READ_WRITE, example = "fernando.dpaula@gmail.com", description = "E-mail do Cliente")
    @NotEmpty
    @Size(max = 100)
    @Email(message = "Deve ser endereço de email válido!")
    private String email;

    @Schema(accessMode = READ_ONLY, example = "2021-06-21T02:00:00", description = "Data e Hora do Pagamento do Cliente")
    private LocalDateTime dataPagamento;

    @Schema(accessMode = READ_WRITE, example = "[A_VISTA, A_PRAZO]", description = "Tipo de Pagamento")
    @NotNull
    private EnTipoPagamento tipo;
}
