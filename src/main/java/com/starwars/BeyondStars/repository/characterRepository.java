package com.starwars.BeyondStars.repository;

import com.starwars.BeyondStars.model.StarWarsCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface characterRepository extends JpaRepository<Character, Long> {

}
