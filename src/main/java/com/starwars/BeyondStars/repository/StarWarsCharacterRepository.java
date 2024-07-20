package com.starwars.BeyondStars.repository;

import com.starwars.BeyondStars.entity.StarWarsCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacterEntity, Long> {

    @Query("SELECT c FROM StarWarsCharacterEntity c " +
            "JOIN c.species s " +
            "JOIN c.films f " +
            "WHERE s.name = :speciesName AND f.title = :filmTitle")
    List<StarWarsCharacterEntity> findBySpeciesAndFilm(@Param("speciesName") String speciesName, @Param("filmTitle") String filmTitle);
}

