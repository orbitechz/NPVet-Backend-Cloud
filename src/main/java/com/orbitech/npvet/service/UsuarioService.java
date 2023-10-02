package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.TipoUsuario;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public UsuarioDTO toUsuarioDTO(Usuario usuarioEntidade){
        return mapper.map(usuarioEntidade, UsuarioDTO.class);
    }

    public Usuario toUsuarioEntidade(UsuarioDTO usuarioDTO){
        return mapper.map(usuarioDTO, Usuario.class);
    }

   public UsuarioDTO getByID(long id){
        return toUsuarioDTO(repository.findById(id).orElse(null));
   }

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream().map(this::toUsuarioDTO).toList();
    }
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
       return toUsuarioDTO(repository.save(toUsuarioEntidade(usuarioDTO)));
    }
    @Transactional
    public UsuarioDTO update(long id, UsuarioDTO usuarioDTO) {
        return toUsuarioDTO(repository.save(toUsuarioEntidade(usuarioDTO)));
    }

    @Transactional
    public void delete(long id){ //TODO: Adicionar uma lista de usuários com agendamento ativo. Se estiver ativo, não deletar.
        UsuarioDTO usuarioDTO = getByID(id);
        usuarioDTO.setDeletedAt(LocalDateTime.now());
        repository.save(toUsuarioEntidade(usuarioDTO));
    }

    public List<UsuarioDTO>getUsuarioByName(String nome){
        List<UsuarioDTO> retorno = repository.findAllUsuariosByNome(nome)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Não encontramos nenhum usuario com o nome {%s}",nome));
        return retorno;
    }

    public List<UsuarioDTO>getTipoSecretaria(){
        TipoUsuario tipoUsuario = null;
        List<UsuarioDTO> retorno = repository.findByTipoUsuario(tipoUsuario.SECRETARIA)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo SECRETARIA cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoAdm(TipoUsuario tipoUsuario){
        List<UsuarioDTO>retorno = repository.findByTipoUsuario(tipoUsuario.ADMINISTRADOR)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo ADMINISTRADOR cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoMedico(TipoUsuario tipoUsuario){
        List<UsuarioDTO>retorno = repository.findByTipoUsuario(tipoUsuario.MEDICO)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo MÉDICO cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getUsername(String username){
        List<UsuarioDTO>retorno = repository.findUsuarioByUsername(username)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Nenhum usuário com o username: {%s} encontrado!",username.toUpperCase()));
        return retorno;
    }

    public List<UsuarioDTO>getUsuarioByCpf(String cpf){
        List<UsuarioDTO>retorno = repository.findUsuarioByCpf(cpf)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),String.format("Nenhum usuário com o CPF: {%s} localizado!",cpf));
        return retorno;
    }
}
