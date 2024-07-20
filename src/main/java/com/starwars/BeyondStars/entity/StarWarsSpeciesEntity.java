package com.starwars.BeyondStars.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StarWarsSpeciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "species")
    private Set<StarWarsCharacterEntity> characters;

    public Long getId() {
        return id;
    }

    public Set<StarWarsCharacterEntity> getCharacters() {
        return characters;
    }

    public String getName() {
        return name;
    }
}
