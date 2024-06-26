package com.eventmanagement.restapi.controller;
import com.eventmanagement.restapi.model.AtividadeModel;
import com.eventmanagement.restapi.model.EspacoModel;
import com.eventmanagement.restapi.model.EventoModel;
import com.eventmanagement.restapi.repository.AtividadeRepository;
import com.eventmanagement.restapi.repository.UsuarioRepository;
import com.eventmanagement.restapi.repository.EdicaoRepository;
import com.eventmanagement.restapi.repository.EspacoRepository;
import com.eventmanagement.restapi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.eventmanagement.restapi.model.EdicaoModel;
import com.eventmanagement.restapi.model.UsuarioModel;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EdicaoRepository edicaoRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/api/events/{eventId}/editions/{editionId}/espacos/{espacoId}/atividades")
    public List<AtividadeModel> getAtividadesByEventoEdicaoEspaco(
            @PathVariable long eventId,
            @PathVariable long editionId,
            @PathVariable long espacoId) {
        return atividadeRepository.findByEventoIdAndEdicaoIdAndEspacoId(eventId, editionId, espacoId);
    }

    @PostMapping("/api/events/{eventId}/editions/{editionId}/espacos/{espacoId}/atividades")
    public String criarAtividadeParaEventoEdicaoEspaco(
            @PathVariable long eventId,
            @PathVariable long editionId,
            @PathVariable long espacoId,
            @RequestBody AtividadeModel novaAtividade) {

        // Verifica se o evento, a edição e o espaço existem
        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        Optional<EdicaoModel> edicaoOptional = edicaoRepository.findById(editionId);
        Optional<EspacoModel> espacoOptional = espacoRepository.findById(espacoId);

        if (eventoOptional.isPresent() && edicaoOptional.isPresent() && espacoOptional.isPresent()) {
            EventoModel evento = eventoOptional.get();
            EdicaoModel edicao = edicaoOptional.get();
            EspacoModel espaco = espacoOptional.get();

            // Define o evento, a edição e o espaço para a nova atividade
            novaAtividade.setEvento(evento);
            novaAtividade.setEdicao(edicao);
            novaAtividade.setEspaco(espaco);

            // Salva a nova atividade
            atividadeRepository.save(novaAtividade);

            return "Atividade criada com sucesso";
        } else {
            return "Evento, Edição ou Espaço não encontrados";
        }
    }

    @PutMapping("/api/events/{eventId}/editions/{editionId}/espacos/{espacoId}/atividades/{atividadeId}")
    public String atualizarAtividadeParaEventoEdicaoEspaco(
            @PathVariable long eventId,
            @PathVariable long editionId,
            @PathVariable long espacoId,
            @PathVariable long atividadeId,
            @RequestBody AtividadeModel atividadeAtualizada) {

        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        Optional<EdicaoModel> edicaoOptional = edicaoRepository.findById(editionId);
        Optional<EspacoModel> espacoOptional = espacoRepository.findById(espacoId);

        // Verifica se o evento, a edição, o espaço e a atividade existem
        if (eventoOptional.isPresent() && edicaoOptional.isPresent()
                && espacoOptional.isPresent() && atividadeRepository.existsById(atividadeId)) {

            EventoModel evento = eventoOptional.get();
            EdicaoModel edicao = edicaoOptional.get();
            EspacoModel espaco = espacoOptional.get();

            // Busca a atividade existente
            Optional<AtividadeModel> atividadeExistenteOptional = atividadeRepository.findById(atividadeId);

            if (atividadeExistenteOptional.isPresent()) {
                AtividadeModel atividadeExistente = atividadeExistenteOptional.get();

                // Atualiza os campos da atividade com os novos valores
                atividadeExistente.setName(atividadeAtualizada.getName());
                atividadeExistente.setDescription(atividadeAtualizada.getDescription());
                atividadeExistente.setTipo(atividadeAtualizada.getTipo());
                atividadeExistente.setData(atividadeAtualizada.getData());
                atividadeExistente.setHorarioinicial(atividadeAtualizada.getHorarioinicial());
                atividadeExistente.setHorariofinal(atividadeAtualizada.getHorariofinal());
                atividadeExistente.setEvento(evento);
                atividadeExistente.setEdicao(edicao);
                atividadeExistente.setEspaco(espaco);

                // Salva a atividade atualizada
                atividadeRepository.save(atividadeExistente);

                return "Atividade atualizada com sucesso";
            } else {
                return "Atividade não encontrada";
            }

        } else {
            return "Evento, Edição, Espaço ou Atividade não encontrados";
        }
    }

    @DeleteMapping("/api/events/{eventId}/editions/{editionId}/espacos/{espacoId}/atividades/{atividadeId}")
    public String excluirAtividadeParaEventoEdicaoEspaco(
            @PathVariable long eventId,
            @PathVariable long editionId,
            @PathVariable long espacoId,
            @PathVariable long atividadeId) {

        Optional<EventoModel> eventoOptional = eventoRepository.findById(eventId);
        Optional<EdicaoModel> edicaoOptional = edicaoRepository.findById(editionId);
        Optional<EspacoModel> espacoOptional = espacoRepository.findById(espacoId);

        // Verifica se o evento, a edição, o espaço e a atividade existem
        if (eventoOptional.isPresent() && edicaoOptional.isPresent()
                && espacoOptional.isPresent() && atividadeRepository.existsById(atividadeId)) {

            // Deleta a atividade
            atividadeRepository.deleteById(atividadeId);

            return "Atividade excluída com sucesso";
        } else {
            return "Evento, Edição, Espaço ou Atividade não encontrados";
        }
    }
    @PostMapping("/api/usuarios/{usuarioId}/atividades/{atividadeId}/favoritar")
    public String favoritarAtividade(
            @PathVariable long usuarioId,
            @PathVariable long atividadeId) {

        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<AtividadeModel> atividadeOptional = atividadeRepository.findById(atividadeId);

        if (usuarioOptional.isPresent() && atividadeOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();
            AtividadeModel atividade = atividadeOptional.get();

            usuario.getAtividadesFavoritas().add(atividade);
            usuarioRepository.save(usuario);

            return "Atividade favoritada com sucesso";
        } else {
            return "Usuário ou Atividade não encontrados";
        }
    }

    @PostMapping("/api/usuarios/{usuarioId}/atividades/{atividadeId}/desfavoritar")
    public String desfavoritarAtividade(
            @PathVariable long usuarioId,
            @PathVariable long atividadeId) {

        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<AtividadeModel> atividadeOptional = atividadeRepository.findById(atividadeId);

        if (usuarioOptional.isPresent() && atividadeOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();
            AtividadeModel atividade = atividadeOptional.get();

            usuario.getAtividadesFavoritas().remove(atividade);
            usuarioRepository.save(usuario);

            return "Atividade desfavoritada com sucesso";
        } else {
            return "Usuário ou Atividade não encontrados";
        }
    }

    @GetMapping("/api/usuarios/{usuarioId}/atividades/favoritas")
    public Set<AtividadeModel> getAtividadesFavoritas(@PathVariable long usuarioId) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(usuarioId);

        return usuarioOptional.map(UsuarioModel::getAtividadesFavoritas).orElse(null);
    }

}

