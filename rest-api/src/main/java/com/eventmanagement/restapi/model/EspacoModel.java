package com.eventmanagement.restapi.model;

import jakarta.persistence.*;

@Entity(name ="espaco")
public class EspacoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false)
    private int capacity;  // Alteração aqui

    @Column(nullable = false, length = 50)
    private String resources;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoModel evento;

    @ManyToOne
    @JoinColumn(name = "edicao_id")
    private EdicaoModel edicao;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
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
}
