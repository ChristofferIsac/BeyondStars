package com.starwars.BeyondStars;

import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.model.StarWarsPlanets;
import com.starwars.BeyondStars.model.StarWarsSpecies;
import com.starwars.BeyondStars.model.StarWarsStarship;
import com.starwars.BeyondStars.service.SwapiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SwapiService swapiService) {
		return args -> {
			Scanner read = new Scanner(System.in);
			String searchType = "";
			String searchId = "";

			while (!searchType.equalsIgnoreCase("exit")) {
				System.out.println("Enter search type (character/planet/species/starship/characterByFilmAndSpecies/exit):");
				searchType = read.nextLine();

				if (searchType.equalsIgnoreCase("exit")) {
					System.out.println("Exiting...");
					break;
				}

				switch (searchType.toLowerCase()) {
					case "character":
						System.out.println("Enter character ID to search:");
						searchId = read.nextLine();
						try {
							StarWarsCharacter character = swapiService.getCharacterData(searchId);
							System.out.println("Name: " + character.getName());
							System.out.println("Gender: " + character.getGender());
							System.out.println("Species: " + character.getSpecies());
							System.out.println("Affiliation: " + character.getAffiliation());
						} catch (IOException | InterruptedException e) {
							System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
						} catch (RuntimeException e) {
							System.err.println(e.getMessage());
						}
						break;
					case "planet":
						System.out.println("Enter planet ID to search:");
						searchId = read.nextLine();
						try {
							StarWarsPlanets planet = swapiService.getPlanetData(searchId);
							System.out.println("Name: " + planet.getName());
							System.out.println("Population: " + planet.getPopulation());
							System.out.println("Terrain: " + planet.getTerrain());
							System.out.println("Climate: " + planet.getClimate());
						} catch (IOException | InterruptedException e) {
							System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
						} catch (RuntimeException e) {
							System.err.println(e.getMessage());
						}
						break;
					case "species":
						System.out.println("Enter species ID to search:");
						searchId = read.nextLine();
						try {
							StarWarsSpecies species = swapiService.getSpeciesDataById(searchId);
							System.out.println("Name: " + species.getName());
							System.out.println("Classification: " + species.getClassification());
							System.out.println("Designation: " + species.getDesignation());
							System.out.println("Language: " + species.getLanguage());
						} catch (IOException | InterruptedException e) {
							System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
						} catch (RuntimeException e) {
							System.err.println(e.getMessage());
						}
						break;
					case "starship":
						System.out.println("Enter starship ID to search:");
						searchId = read.nextLine();
						try {
							StarWarsStarship starship = swapiService.getStarshipData(searchId);
							System.out.println("Name: " + starship.getName());
							System.out.println("Model: " + starship.getModel());
							System.out.println("Starship Class: " + starship.getStarshipClass());
							System.out.println("Cost: " + starship.getCost());
						} catch (IOException | InterruptedException e) {
							System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
						} catch (RuntimeException e) {
							System.err.println(e.getMessage());
						}
						break;
					case "characterbyfilmandspecies":
						System.out.println("Enter film title to search:");
						String filmTitle = read.nextLine();
						System.out.println("Enter species name to search:");
						String speciesName = read.nextLine();
						try {
							List<StarWarsCharacter> characters = swapiService.getCharactersByFilmAndSpecies(filmTitle, speciesName);
							characters.forEach(character -> {
								System.out.println("Name: " + character.getName());
								System.out.println("Gender: " + character.getGender());
								System.out.println("Species: " + character.getSpecies());
								System.out.println("Affiliation: " + character.getAffiliation());
							});
						} catch (RuntimeException e) {
							System.err.println(e.getMessage());
						}
						break;
					default:
						System.out.println("Invalid search type. Try again.");
				}
			}

			read.close();
		};
	}
}