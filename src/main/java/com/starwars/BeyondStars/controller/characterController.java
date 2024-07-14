package com.starwars.BeyondStars.controller;

import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.service.characterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class characterController {
    @Autowired
    private characterService characterService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCharacters", characterService.getAllCharacters());
        return "index";
    }

    @GetMapping("/showNewCharacterForm")
    public String showNewCharacterForm(Model model) {

        StarWarsCharacter character = new StarWarsCharacter();

        model.addAttribute("character", character);
        return "new_character";
    }

    @PostMapping("/saveCharacter")
    public String saveCharacter(@ModelAttribute("character") Character character) {
        characterService.saveCharacter(character);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Character character = characterService.getCharacterById(id);
        model.addAttribute("character", character);
        return "update_character";
    }

    @GetMapping("/deleteCharacter/{id}")
    public String deleteCharacter(@PathVariable(value = "id") long id) {
        characterService.deleteCharacter(id);
        return "redirect:/";
    }
}