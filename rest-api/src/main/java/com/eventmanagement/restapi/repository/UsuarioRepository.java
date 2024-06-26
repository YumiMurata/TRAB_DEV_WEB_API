package com.eventmanagement.restapi.repository;

import com.eventmanagement.restapi.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
}
