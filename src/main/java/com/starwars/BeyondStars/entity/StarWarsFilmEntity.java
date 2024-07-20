package com.starwars.BeyondStars.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StarWarsFilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "films")
    private Set<StarWarsCharacterEntity> characters;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<StarWarsCharacterEntity> getCharacters() {
        return characters;
    }
}
