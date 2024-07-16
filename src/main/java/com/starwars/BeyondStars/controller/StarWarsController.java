package com.starwars.BeyondStars.controller;

import com.starwars.BeyondStars.SwapiService;
import com.starwars.BeyondStars.model.StarWarsCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Optional;

@Controller
public class StarWarsController {

    @Autowired
    private SwapiService swapiService;

    @GetMapping("/character/{id}")
    public String getCharacter(@PathVariable String id, Model model) {
        try {
            StarWarsCharacter character = swapiService.getCharacterData(id);
            model.addAttribute("character", character);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            model.addAttribute("error", "Could not retrieve character data");
        }
        return "character_view"; // Nome do template HTML para exibir o personagem
    }
}