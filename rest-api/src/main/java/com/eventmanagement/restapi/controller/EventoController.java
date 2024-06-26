package com.eventmanagement.restapi.controller;

import com.eventmanagement.restapi.model.EventoModel;
import com.eventmanagement.restapi.model.UsuarioModel;
import com.eventmanagement.restapi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping (path= "/api/events")
    public List<EventoModel> getEvents(){

        return (List<EventoModel>)
                eventoRepository.findAll();
}
    @PostMapping(path ="/api/events/cadastro")
    public String salvar(@RequestBody EventoModel evento){
        eventoRepository.save(evento);
        return "Saved event...";
}
    @PutMapping(path ="/api/events/atualizar/{id}")
    public String atualizar (@PathVariable long id, @RequestBody EventoModel evento){
        EventoModel updatedEventoModel = eventoRepository.findById(id).get();
        updatedEventoModel.setName(evento.getName());
        updatedEventoModel.setAbbreviation(evento.getAbbreviation());
        updatedEventoModel.setDescription(evento.getDescription());
        eventoRepository.save(updatedEventoModel);
        return "Updated...";
    }
    @DeleteMapping (path ="/api/events/deletar/{id}")
    public String deletar (@PathVariable long id){
        EventoModel deletar = eventoRepository.findById(id).get();
        eventoRepository.delete(deletar);
        return "Deleted..."+id;
    }

}