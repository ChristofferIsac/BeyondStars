package com.starwars.BeyondStars.service;

import com.starwars.BeyondStars.entity.StarWarsCharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.starwars.BeyondStars.repository.StarWarsCharacterRepository;
import  org.springframework.stereotype.Service;

import java.util.List;

@Service
public class characterService {
    @Autowired
    private StarWarsCharacterRepository StarWarsCharacterRepository;

    public List<StarWarsCharacterEntity> getAllCharacters() {
        return StarWarsCharacterRepository.findAll();
    }

    public void saveCharacter(Character character) {

    }

    public StarWarsCharacterEntity getCharacterById(Long id) {

        return StarWarsCharacterRepository.findById(id).orElse(null);
    }

    public void deleteCharacter(Long id) {
        StarWarsCharacterRepository.deleteById(id);
    }
}
