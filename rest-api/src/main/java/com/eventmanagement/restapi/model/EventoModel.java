package com.eventmanagement.restapi.model;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity(name = "evento")


public class EventoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    public String name;
    @Column(nullable = false, length = 50)
    public String abbreviation;
    @Column(nullable = false, length = 50)
    public String description;

    @OneToMany(mappedBy = "eventos")
    private Set<EdicaoModel> editions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EdicaoModel> getEditions() {
        return editions;
    }

    public void setEditions(Set<EdicaoModel> editions) {
        this.editions = editions;
    }


}
