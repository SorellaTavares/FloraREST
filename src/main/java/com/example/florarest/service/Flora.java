package com.example.florarest.service;


import jakarta.persistence.*;

@Entity
@Table(name="flora")
public class Flora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="name")
    String name;
    @Column(name="scientificname")
    String sciName;


    public Flora(String name, String sciName) {
        this.name = name;
        this.sciName = sciName;
    }

    public Flora() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }
}
