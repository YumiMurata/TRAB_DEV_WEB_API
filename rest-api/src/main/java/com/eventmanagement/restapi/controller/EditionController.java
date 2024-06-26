package com.eventmanagement.restapi.controller;

import com.eventmanagement.restapi.model.EdicaoModel;
import com.eventmanagement.restapi.repository.EdicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.eventmanagement.restapi.model.EventoModel;
import com.eventmanagement.restapi.repository.EventoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

@RestController
public class EditionController {
    @Autowired
    private EdicaoRepository edicaoRepository;
    @Autowired
    private EventoRepository eventoRepository;


    @GetMapping(path = "/api/events/{eventId}/editions")
    public List<EdicaoModel> getEventEditions(@PathVariable long eventId) {
        return edicaoRepository.findByEventos_Id(eventId);
    }

    @PostMapping(path = "/api/events/{eventId}/editions")
    public String criarEdicaoParaEvento(
            @PathVariable long eventId,
            @RequestBody EdicaoModel novaEdicao) {

        // Verifica se o evento existe
        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        if (eventoOptional.isPresent()) {
            EventoModel evento = eventoOptional.get();

            // Define o evento para a nova edição
            novaEdicao.setEvento(Collections.singleton(evento)); // Assuming evento is a single event

            // Salva a nova edição
            edicaoRepository.save(novaEdicao);

            return "Edição criada com sucesso";
        } else {
            return "Evento não encontrado";
        }
    }
    @PutMapping(path = "/api/events/{eventId}/editions/{editionId}")
    public String atualizarEdicaoParaEvento(
            @PathVariable long eventId,
            @PathVariable long editionId,
            @RequestBody EdicaoModel edicaoAtualizada) {

        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        Optional<EdicaoModel> edicaoOptional = edicaoRepository.findById(editionId);

        if (eventoOptional.isPresent() && edicaoOptional.isPresent()) {
            EventoModel evento = eventoOptional.get();
            EdicaoModel edicao = edicaoOptional.get();

            // Verifica se a edição pertence ao evento
            if (edicao.getEventos().contains(evento)) {
                // Atualiza os dados da edição
                edicao.setNumber(edicaoAtualizada.getNumber());
                edicao.setYear(edicaoAtualizada.getYear());
                edicao.setStartDate(edicaoAtualizada.getStartDate());
                edicao.setEndDate(edicaoAtualizada.getEndDate());
                edicao.setCity(edicaoAtualizada.getCity());


                // Salva a edição atualizada
                edicaoRepository.save(edicao);

                return "Edição atualizada com sucesso";
            } else {
                return "A edição não pertence ao evento especificado";
            }
        } else {
            return "Evento ou edição não encontrado";
        }
    }
    @DeleteMapping(path = "/api/events/{eventId}/editions/{editionId}")
    public String deletarEdicaoParaEvento(
            @PathVariable long eventId,
            @PathVariable long editionId) {

        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        Optional<EdicaoModel> edicaoOptional = edicaoRepository.findById(editionId);

        if (eventoOptional.isPresent() && edicaoOptional.isPresent()) {
            EventoModel evento = eventoOptional.get();
            EdicaoModel edicao = edicaoOptional.get();

            // Verifica se a edição pertence ao evento
            if (edicao.getEventos().contains(evento)) {
                // Remove a edição do evento
                evento.getEditions().remove(edicao); // Alteração aqui

                // Remove o evento da edição (opcional, dependendo da sua lógica)
                edicao.getEventos().remove(evento); // Alteração aqui

                // Remove a edição do repositório
                edicaoRepository.delete(edicao);

                return "Edição deletada com sucesso";
            } else {
                return "A edição não pertence ao evento especificado";
            }
        } else {
            return "Evento ou edição não encontrado";
        }
    }

}
