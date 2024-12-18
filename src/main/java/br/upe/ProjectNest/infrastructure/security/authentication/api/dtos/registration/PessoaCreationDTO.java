package br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Pessoa}
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public record PessoaCreationDTO(
    @NotNull @Size(max = 50)
    String apelido,
    @NotNull @Size(max = 255) @Email
    String email,
    @NotEmpty @Size(min = 6)
    String senha,
    @NotNull @Size(max = 50)
    String nome,
    @NotNull @Size(max = 50)
    String sobrenome,
    @Size(max = 25)
    String pronomes) implements UsuarioCreationDTO {
}