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
        Usuario usuarioByCpf = repository.findUsuarioByCpf(usuarioDTO.getCpf());

        Assert.isTrue(usuarioByCpf == null, String.format("Usuário com o CPF: {%s} já existe!",usuarioDTO.getCpf()));

       return toUsuarioDTO(repository.save(toUsuarioEntidade(usuarioDTO)));
    }
    @Transactional
    public UsuarioDTO update(long id, UsuarioDTO usuarioDTO) {
        return toUsuarioDTO(repository.save(toUsuarioEntidade(usuarioDTO)));
    }

    @Transactional
    public void delete(long id){
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
        List<UsuarioDTO> retorno = repository.findByTipoUsuario(TipoUsuario.SECRETARIA)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo SECRETARIA cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoAdm(){
        List<UsuarioDTO>retorno = repository.findByTipoUsuario(TipoUsuario.ADMINISTRADOR)
                .stream()
                .map(this::toUsuarioDTO)
                .toList();
        Assert.isTrue(!retorno.isEmpty(),"Nenhum usuário do tipo ADMINISTRADOR cadastrado!");
        return retorno;
    }

    public List<UsuarioDTO>getTipoMedico(){
        List<UsuarioDTO>retorno = repository.findByTipoUsuario(TipoUsuario.MEDICO)
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

    public UsuarioDTO getUsuarioByCpf(String cpf){
        UsuarioDTO retorno = toUsuarioDTO(repository.findUsuarioByCpf(cpf));
        Assert.notNull(retorno, String.format("Nenhum usuário com o CPF: {%s} localizado!",cpf));
        return retorno;
    }
}
