package com.eventmanagement.restapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name ="edition")
public class EdicaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    public int number;
    @Column(nullable = false, length = 4)
    public int year;
    @Column(nullable = false, length = 10)
    public String startDate;
    @Column(nullable = false, length = 10)
    public String endDate;
    @Column(nullable = false, length = 50)
    public String city;

    @ManyToMany
    @JoinTable(
            name = "evento_edition",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )

    private Set<EventoModel> eventos;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<EventoModel> getEventos() {
        return eventos;
    }
    public void setEvento(Set<EventoModel> eventos) {
        this.eventos = eventos;
    }



}