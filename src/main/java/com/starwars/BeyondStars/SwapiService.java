package com.starwars.BeyondStars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.model.StarWarsPlanets;
import com.starwars.BeyondStars.model.StarWarsSpecies;
import com.starwars.BeyondStars.model.StarWarsStarship;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SwapiService {

    private final HttpClient client;
    private final Gson gson;

    public SwapiService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().create();
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

            String name = characterJsonObject.get("name").getAsString();
            String gender = characterJsonObject.get("gender").getAsString();
            String speciesUrl = characterJsonObject.getAsJsonArray("species").size() > 0 ? characterJsonObject.getAsJsonArray("species").get(0).getAsString() : null;

            String species = getSpeciesData(speciesUrl);

            return new StarWarsCharacter(name, gender, species, "Unknown Affiliation");
        } else {
            throw new RuntimeException("Failed to get character data from SWAPI");
        }
    }

    private String getSpeciesData(String speciesUrl) throws IOException, InterruptedException {
        if (speciesUrl == null) return "unknown";

        HttpRequest speciesRequest = HttpRequest.newBuilder()
                .uri(URI.create(speciesUrl))
                .GET()
                .build();

        HttpResponse<String> speciesResponse = client.send(speciesRequest, HttpResponse.BodyHandlers.ofString());

        if (speciesResponse.statusCode() == 200) {
            JsonObject speciesJsonObject = gson.fromJson(speciesResponse.body(), JsonObject.class);
            return speciesJsonObject.get("name").getAsString();
        } else {
            throw new RuntimeException("Failed to get species data from SWAPI");
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

            String name = planetJsonObject.get("name").getAsString();
            int population = planetJsonObject.get("population").getAsInt();
            String terrain = planetJsonObject.get("terrain").getAsString();
            String climate = planetJsonObject.get("climate").getAsString();

            return new StarWarsPlanets(name, population, terrain, climate);
        } else {
            throw new RuntimeException("Failed to get planet data from SWAPI");
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

            String name = speciesJsonObject.get("name").getAsString();
            String classification = speciesJsonObject.get("classification").getAsString();
            String language = speciesJsonObject.get("language").getAsString();

            return new StarWarsSpecies(name, classification, language);
        } else {
            throw new RuntimeException("Failed to get species data from SWAPI");
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

            String name = starshipJsonObject.get("name").getAsString();
            String model = starshipJsonObject.get("model").getAsString();
            int cost = starshipJsonObject.get("cost").getAsInt();
            String manufacturer = starshipJsonObject.get("manufacturer").getAsString();

            return new StarWarsStarship(name, model, cost );
        } else {
            throw new RuntimeException("Failed to get starship data from SWAPI");
        }
    }

    public static void main(String[] args) {
        SwapiService service = new SwapiService();
        Scanner read = new Scanner(System.in);
        String searchType = "";
        String searchId = "";

        while (!searchType.equalsIgnoreCase("exit")) {
            System.out.println("Enter search type (character/planet/species/starship/exit):");
            searchType = read.nextLine();

            if (searchType.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println("Enter ID to search:");
            searchId = read.nextLine();

            try {
                switch (searchType.toLowerCase()) {
                    case "character":
                        StarWarsCharacter character = service.getCharacterData(searchId);
                        System.out.println("Name: " + character.getName());
                        System.out.println("Gender: " + character.getGender());
                        System.out.println("Species: " + character.getSpecies());
                        System.out.println("Affiliation: " + character.getAffiliation());
                        break;
                    case "planet":
                        StarWarsPlanets planet = service.getPlanetData(searchId);
                        System.out.println("Name: " + planet.getName());
                        System.out.println("Population: " + planet.getPopulation());
                        System.out.println("Terrain: " + planet.getTerrain());
                        System.out.println("Climate: " + planet.getClimate());
                        break;
                    case "species":
                        StarWarsSpecies species = service.getSpeciesDataById(searchId);
                        System.out.println("Name: " + species.getName());
                        System.out.println("Classification: " + species.getClassification());
                        System.out.println("Language: " + species.getLanguage());
                        break;
                    case "starship":
                        StarWarsStarship starship = service.getStarshipData(searchId);
                        System.out.println("Name: " + starship.getName());
                        System.out.println("Model: " + starship.getModel());
                        System.out.println("Cost: " + starship.getCost());
                        break;
                    default:
                        System.out.println("Invalid search type. Try again.");
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
            }
        }

        read.close();
    }
}


