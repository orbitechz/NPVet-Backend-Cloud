package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

   public UsuarioDTO getByID(long id) throws Exception{
        return toUsuarioDTO(repository.findById(id).orElseThrow(() -> new Exception(String.format("Usuário com o id [%s] não localizado.",id))));
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
        Assert.notNull(repository.findById(id).orElse(null),String.format("ID [%s] não localizado.",id));
        repository.deleteById(id);
    }
}
