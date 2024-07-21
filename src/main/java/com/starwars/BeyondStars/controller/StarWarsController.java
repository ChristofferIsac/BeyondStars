package com.starwars.BeyondStars.controller;

import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class StarWarsController {

    @Autowired
    private SwapiService swapiService;

    @GetMapping("/character/{id}")
    public StarWarsCharacter getCharacter(@PathVariable String id) throws IOException, InterruptedException {
        return swapiService.getCharacterData(id);
    }


}
