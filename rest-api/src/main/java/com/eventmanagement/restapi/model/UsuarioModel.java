package com.eventmanagement.restapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity (name ="usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 50)
    public String login;
    @Column (nullable = false, length = 50)
    public String name;
    @Column (nullable = false, length = 50)
    public String email;
    @Column (nullable = false, length = 50)
    public String affiliation;

    @ManyToMany
    private Set<AtividadeModel>atividadesFavoritas;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public Set<AtividadeModel> getAtividadesFavoritas() {
        return atividadesFavoritas;
    }

    public void setAtividadesFavoritas(Set<AtividadeModel> atividadesFavoritas) {
        this.atividadesFavoritas = atividadesFavoritas;
    }
}
