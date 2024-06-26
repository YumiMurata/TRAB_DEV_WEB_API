package com.eventmanagement.restapi.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity(name = "atividade")
public class AtividadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 500)
    private String tipo;

    @Column(nullable = false, length = 500)
    private String data;


    @Column(nullable = false, length = 500)
    private String horarioinicial;

    @Column(nullable = false, length = 500)
    private String horariofinal;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private EdicaoModel edicao;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventoModel evento;

    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private EspacoModel espaco;

    @ManyToMany(mappedBy = "atividadesFavoritas")
    private Set<UsuarioModel> usuariosFavoritaram;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioinicial() {
        return horarioinicial;
    }

    public void setHorarioinicial(String horarioinicial) {
        this.horarioinicial = horarioinicial;
    }

    public String getHorariofinal() {
        return horariofinal;
    }

    public void setHorariofinal(String horariofinal) {
        this.horariofinal = horariofinal;
    }

    public EventoModel getEvento() {
        return evento;
    }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }

    public EdicaoModel getEdicao() {
        return edicao;
    }

    public void setEdicao(EdicaoModel edicao) {
        this.edicao = edicao;
    }

    public EspacoModel getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoModel espaco) {
        this.espaco = espaco;
    }

    public Set<UsuarioModel> getUsuariosFavoritaram() {
        return usuariosFavoritaram;
    }

    public void setUsuariosFavoritaram(Set<UsuarioModel> usuariosFavoritaram) {
        this.usuariosFavoritaram = usuariosFavoritaram;
    }
}
