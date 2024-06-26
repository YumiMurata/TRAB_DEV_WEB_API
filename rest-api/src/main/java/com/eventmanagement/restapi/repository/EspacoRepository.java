package com.eventmanagement.restapi.repository;

import com.eventmanagement.restapi.model.EspacoModel;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EspacoRepository extends CrudRepository<EspacoModel, Long> {
    List<EspacoModel> findByEventoIdAndEdicaoId(Long eventoId, Long edicaoId);
}
