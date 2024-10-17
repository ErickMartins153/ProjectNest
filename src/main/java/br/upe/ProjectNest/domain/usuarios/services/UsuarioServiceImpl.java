package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.repositories.EmpresaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.PessoaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioMapper usuarioMapper;

    UsuarioCreationMapper usuarioCreationMapper;

    UsuarioRepository usuarioRepository;

    EmpresaRepository empresaRepository;

    PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public UsuarioDTO registerUsuario(UsuarioCreationDTO dto) {
        Usuario usuario = usuarioCreationMapper.toEntity(dto);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public Optional<UsuarioDTO> getByUuid(UUID uuid) {
        Optional<Usuario> usuario = usuarioRepository.findById(uuid);
        return usuario.map(value -> usuarioMapper.toDto(value));
    }

    @Override
    public Optional<UsuarioDTO> getByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.map(value -> usuarioMapper.toDto(value));
    }

    @Override
    public PagedModel<UsuarioDTO> searchByApelido(String apelido, Pageable pageable) {
        return new PagedModel<>(
            usuarioRepository.searchByApelidoContains(apelido, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable) {
        return new PagedModel<>(
            pessoaRepository.searchByApelidoContains(apelido, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable) {
        return new PagedModel<>(
            empresaRepository.searchByApelidoContains(empresa, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<EmpresaDTO> getAllEmpresas(Pageable pageable) {
        return new PagedModel<>(
            empresaRepository.findAll(pageable).map(usuarioMapper::toDto));
    }

    @Override
    public Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj) {
        return empresaRepository.findByCnpj(cnpj).map(value -> usuarioMapper.toDto(value));
    }

    @Override
    @Transactional
    public UsuarioDTO update(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void deleteUsuario(UUID uuid) {
        usuarioRepository.deleteById(uuid);
    }

}
