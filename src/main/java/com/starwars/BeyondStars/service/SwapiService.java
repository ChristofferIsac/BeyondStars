package com.starwars.BeyondStars.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starwars.BeyondStars.entity.StarWarsCharacterEntity;
import com.starwars.BeyondStars.repository.StarWarsCharacterRepository;
import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.model.StarWarsPlanets;
import com.starwars.BeyondStars.model.StarWarsSpecies;
import com.starwars.BeyondStars.model.StarWarsStarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class SwapiService {

    private final HttpClient client;
    private final Gson gson;

    @Autowired
    private StarWarsCharacterRepository characterRepository;

    public SwapiService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().create();
    }

    private String getStringOrEmpty(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        return element != null && !element.isJsonNull() ? element.getAsString() : "";
    }

    private int getIntOrZero(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        if (element != null && !element.isJsonNull()) {
            try {
                return Integer.parseInt(element.getAsString());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse " + key + " as int: " + element.getAsString());
            }
        }
        return 0;
    }

    private long getLongOrZero(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        if (element != null && !element.isJsonNull()) {
            try {
                return Long.parseLong(element.getAsString());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse " + key + " as long: " + element.getAsString());
            }
        }
        return 0L;
    }

    public StarWarsCharacter getCharacterData(String characterId) throws IOException, InterruptedException {
        String characterURL = "https://swapi.dev/api/people/" + characterId + "/";
        HttpRequest characterRequest = HttpRequest.newBuilder()
                .uri(URI.create(characterURL))
                .GET()
                .build();

        HttpResponse<String> characterResponse = client.send(characterRequest, HttpResponse.BodyHandlers.ofString());

        if (characterResponse.statusCode() == 200) {
            String characterJson = characterResponse.body();
            JsonObject characterJsonObject = gson.fromJson(characterJson, JsonObject.class);

            String name = getStringOrEmpty(characterJsonObject, "name");
            String gender = getStringOrEmpty(characterJsonObject, "gender");
            String speciesUrl = characterJsonObject.getAsJsonArray("species").size() > 0
                    ? characterJsonObject.getAsJsonArray("species").get(0).getAsString()
                    : "";

            String species = getSpeciesData(speciesUrl);

            return new StarWarsCharacter(name, gender, species, "Unknown Affiliation");
        } else {
            throw new RuntimeException("Failed to get character data from SWAPI. HTTP status code: " + characterResponse.statusCode());
        }
    }

    private String getSpeciesData(String speciesUrl) throws IOException, InterruptedException {
        if (speciesUrl == null || speciesUrl.isEmpty()) return "";

        HttpRequest speciesRequest = HttpRequest.newBuilder()
                .uri(URI.create(speciesUrl))
                .GET()
                .build();

        HttpResponse<String> speciesResponse = client.send(speciesRequest, HttpResponse.BodyHandlers.ofString());

        if (speciesResponse.statusCode() == 200) {
            JsonObject speciesJsonObject = gson.fromJson(speciesResponse.body(), JsonObject.class);
            return getStringOrEmpty(speciesJsonObject, "name");
        } else {
            throw new RuntimeException("Failed to get species data from SWAPI. HTTP status code: " + speciesResponse.statusCode());
        }
    }

    public StarWarsPlanets getPlanetData(String planetId) throws IOException, InterruptedException {
        String planetURL = "https://swapi.dev/api/planets/" + planetId + "/";
        HttpRequest planetRequest = HttpRequest.newBuilder()
                .uri(URI.create(planetURL))
                .GET()
                .build();

        HttpResponse<String> planetResponse = client.send(planetRequest, HttpResponse.BodyHandlers.ofString());

        if (planetResponse.statusCode() == 200) {
            String planetJson = planetResponse.body();
            JsonObject planetJsonObject = gson.fromJson(planetJson, JsonObject.class);

            String name = getStringOrEmpty(planetJsonObject, "name");
            long population = getLongOrZero(planetJsonObject, "population");
            String terrain = getStringOrEmpty(planetJsonObject, "terrain");
            String climate = getStringOrEmpty(planetJsonObject, "climate");

            return new StarWarsPlanets(name, population, terrain, climate);
        } else {
            throw new RuntimeException("Failed to get planet data from SWAPI. HTTP status code: " + planetResponse.statusCode());
        }
    }

    public StarWarsSpecies getSpeciesDataById(String speciesId) throws IOException, InterruptedException {
        String speciesURL = "https://swapi.dev/api/species/" + speciesId + "/";
        HttpRequest speciesRequest = HttpRequest.newBuilder()
                .uri(URI.create(speciesURL))
                .GET()
                .build();

        HttpResponse<String> speciesResponse = client.send(speciesRequest, HttpResponse.BodyHandlers.ofString());

        if (speciesResponse.statusCode() == 200) {
            String speciesJson = speciesResponse.body();
            JsonObject speciesJsonObject = gson.fromJson(speciesJson, JsonObject.class);

            String name = getStringOrEmpty(speciesJsonObject, "name");
            String classification = getStringOrEmpty(speciesJsonObject, "classification");
            String designation = getStringOrEmpty(speciesJsonObject, "designation");
            String language = getStringOrEmpty(speciesJsonObject, "language");

            return new StarWarsSpecies(name, classification, designation, language);
        } else {
            throw new RuntimeException("Failed to get species data from SWAPI. HTTP status code: " + speciesResponse.statusCode());
        }
    }

    public StarWarsStarship getStarshipData(String starshipId) throws IOException, InterruptedException {
        String starshipURL = "https://swapi.dev/api/starships/" + starshipId + "/";
        HttpRequest starshipRequest = HttpRequest.newBuilder()
                .uri(URI.create(starshipURL))
                .GET()
                .build();

        HttpResponse<String> starshipResponse = client.send(starshipRequest, HttpResponse.BodyHandlers.ofString());

        if (starshipResponse.statusCode() == 200) {
            String starshipJson = starshipResponse.body();
            JsonObject starshipJsonObject = gson.fromJson(starshipJson, JsonObject.class);

            String name = getStringOrEmpty(starshipJsonObject, "name");
            String model = getStringOrEmpty(starshipJsonObject, "model");
            String starshipClass = getStringOrEmpty(starshipJsonObject, "starship_class");
            long cost = getLongOrZero(starshipJsonObject, "cost_in_credits");

            return new StarWarsStarship(name, model, starshipClass, cost);
        } else {
            throw new RuntimeException("Failed to get starship data from SWAPI. HTTP status code: " + starshipResponse.statusCode());
        }
    }

    public List<StarWarsCharacter> getCharactersByFilmAndSpecies(String filmTitle, String speciesName) {
        List<StarWarsCharacterEntity> characterEntities = characterRepository.findBySpeciesAndFilm(speciesName, filmTitle);
        return characterEntities.stream()
                .map(entity -> new StarWarsCharacter(
                        entity.getName(),
                        entity.getGender(),
                        entity.getSpecies().stream().map(species -> species.getName()).collect(Collectors.joining(", ")),
                        "Unknown Affiliation"
                ))
                .collect(Collectors.toList());
    }
}
