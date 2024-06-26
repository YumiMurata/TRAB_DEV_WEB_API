package com.eventmanagement.restapi.repository;

import com.eventmanagement.restapi.model.EdicaoModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EdicaoRepository extends CrudRepository<EdicaoModel, Long> {
    List<EdicaoModel> findByEventos_Id(long eventoId);
}


