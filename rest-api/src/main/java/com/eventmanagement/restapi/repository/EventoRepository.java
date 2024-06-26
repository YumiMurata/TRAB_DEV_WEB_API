package com.eventmanagement.restapi.repository;

import com.eventmanagement.restapi.model.EventoModel;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<EventoModel, Long> {
}
