package com.eventmanagement.restapi.controller;

import com.eventmanagement.restapi.model.UsuarioModel;
import com.eventmanagement.restapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;


    @GetMapping (path= "/api/usuario")
    public List<UsuarioModel>getUsers(){
        return (List<UsuarioModel>) repository.findAll();

    }
    @PostMapping (path ="/api/usuario/cadastro")
    public String salvar(@RequestBody UsuarioModel usuario){
         repository.save(usuario);
         return "Saved...";
    }
    @PutMapping (path ="/api/usuario/atualizar/{id}")
    public String atualizar (@PathVariable long id, @RequestBody UsuarioModel usuario){
        UsuarioModel updatedUsuarioModel = repository.findById(id).get();
        updatedUsuarioModel.setAffiliation(usuario.getAffiliation());
        updatedUsuarioModel.setEmail(usuario.getEmail());
        updatedUsuarioModel.setLogin(usuario.getLogin());
        updatedUsuarioModel.setName(usuario.getName());
        repository.save(updatedUsuarioModel);
        return "Updated...";
    }
    @DeleteMapping (path ="/api/usuario/deletar/{id}")
    public String deletar (@PathVariable long id){
        UsuarioModel deletar = repository.findById(id).get();
        repository.delete(deletar);
        return "Deleted..."+id;
    }
}
