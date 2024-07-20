package com.starwars.BeyondStars.repository;

import com.starwars.BeyondStars.entity.StarWarsCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacterEntity, Long> {
    List<StarWarsCharacterEntity> findBySpeciesAndFilm(String speciesName, String filmTitle);
}

