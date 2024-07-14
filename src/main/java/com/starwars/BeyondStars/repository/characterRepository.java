package com.starwars.BeyondStars.repository;

import com.starwars.BeyondStars.model.characterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface characterRepository extends JpaRepository<Character, Long> {

}
