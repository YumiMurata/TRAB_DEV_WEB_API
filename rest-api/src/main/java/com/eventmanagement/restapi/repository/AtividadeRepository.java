package com.eventmanagement.restapi.repository;

import com.eventmanagement.restapi.model.AtividadeModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtividadeRepository extends CrudRepository<AtividadeModel, Long>{
    List<AtividadeModel> findByEventoIdAndEdicaoIdAndEspacoId(long eventId, long editionId, long espacoId);
}
