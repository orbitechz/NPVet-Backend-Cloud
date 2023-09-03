package com.orbitech.npvet.Service;

import com.orbitech.npvet.DTO.UsuarioDTO;
import com.orbitech.npvet.Entity.Usuario;
import com.orbitech.npvet.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public UsuarioDTO ToUsuarioDTO(Usuario usuarioEntidade){
        return mapper.map(usuarioEntidade, UsuarioDTO.class);
    }

    public Usuario ToUsuarioEntidade(UsuarioDTO usuarioDTO){
        return mapper.map(usuarioDTO, Usuario.class);
    }

   public UsuarioDTO GetByID(long id) throws Exception{
        return ToUsuarioDTO(repository.findById(id).orElseThrow(() -> new Exception(String.format("Usuário com o id [%s] não localizado.",id))));
   }





}
