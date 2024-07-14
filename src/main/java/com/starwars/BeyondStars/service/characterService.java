package com.starwars.BeyondStars.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.starwars.BeyondStars.model.characterModel;
import com.starwars.BeyondStars.repository.characterRepository;
import  org.springframework.stereotype.Service;

import java.util.List;

@Service
public class characterService {
    @Autowired
    private characterRepository characterRepository;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public void saveCharacter(Character character) {
        characterRepository.save(character);
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}
