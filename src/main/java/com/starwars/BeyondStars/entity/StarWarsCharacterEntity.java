package com.starwars.BeyondStars.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StarWarsCharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "character_species",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "species_id")
    )
    private Set<StarWarsSpeciesEntity> species;

    @ManyToMany
    @JoinTable(
            name = "character_films",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<StarWarsFilmEntity> films;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<StarWarsSpeciesEntity> getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public Set<StarWarsFilmEntity> getFilms() {
        return films;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSpecies(Set<StarWarsSpeciesEntity> species) {
        this.species = species;
    }

    public void setFilms(Set<StarWarsFilmEntity> films) {
        this.films = films;
    }
}
