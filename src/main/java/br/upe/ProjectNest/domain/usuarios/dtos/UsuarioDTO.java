package br.upe.ProjectNest.domain.usuarios.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Set;
import java.util.UUID;

@JsonTypeInfo(use=JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@Type(EmpresaDTO.class), @Type(PessoaDTO.class)})
public interface UsuarioDTO {

    UsuarioType type();

    UUID uuid();

    String apelido();

    String email();

    Set<String> roles();

}
