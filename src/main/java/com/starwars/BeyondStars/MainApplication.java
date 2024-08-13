package com.starwars.BeyondStars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starwars.BeyondStars.model.StarWarsCharacter;
import com.starwars.BeyondStars.model.StarWarsPlanets;
import com.starwars.BeyondStars.model.StarWarsSpecies;
import com.starwars.BeyondStars.model.StarWarsStarship;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainApplication {

    private final HttpClient client;
    private final Gson gson;


    public MainApplication() {
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
            String vehiclesUrl = characterJsonObject.getAsJsonArray("vehicles").size() > 0
                    ? characterJsonObject.getAsJsonArray("vehicles").get(0).getAsString()
                    : "";
            String starshipsUrl = characterJsonObject.getAsJsonArray("starships").size() > 0
                    ? characterJsonObject.getAsJsonArray("starships").get(0).getAsString()
                    : "";

            String vehicles = getVehiclesData(vehiclesUrl);
            String species = getSpeciesData(speciesUrl);
            String starships = getStarshipsData(starshipsUrl);


            return new StarWarsCharacter(name, gender, species, vehicles, starships);
        } else {
            throw new RuntimeException("Failed to get character data from SWAPI. Status Code " + characterResponse.statusCode());
        }
    }

    private String getSpeciesData(String speciesUrl) throws IOException, InterruptedException {
        if (speciesUrl == null || speciesUrl.isEmpty()) return "N/A";

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

    private String getVehiclesData(String vehiclesUrl) throws IOException, InterruptedException {
        if (vehiclesUrl == null || vehiclesUrl.isEmpty()) return "N/A";

        HttpRequest vehiclesRequest = HttpRequest.newBuilder()
                .uri(URI.create(vehiclesUrl))
                .GET()
                .build();

        HttpResponse<String> vehiclesResponse = client.send(vehiclesRequest, HttpResponse.BodyHandlers.ofString());

        if (vehiclesResponse.statusCode() == 200) {
            JsonObject vehiclesJsonObject = gson.fromJson(vehiclesResponse.body(), JsonObject.class);
            return getStringOrEmpty(vehiclesJsonObject, "name");
        } else {
            throw new RuntimeException("Failed to get vehicles data from SWAPI. HTTP status code: " + vehiclesResponse.statusCode());
          }
    }

    private String getStarshipsData(String starshipsUrl) throws IOException, InterruptedException {
        if (starshipsUrl == null || starshipsUrl.isEmpty()) return "N/A";

        HttpRequest starshipsRequest = HttpRequest.newBuilder()
                .uri(URI.create(starshipsUrl))
                .GET()
                .build();

        HttpResponse<String> starshipsResponse = client.send(starshipsRequest, HttpResponse.BodyHandlers.ofString());

        if (starshipsResponse.statusCode() == 200) {
            JsonObject starshipsJsonObject = gson.fromJson(starshipsResponse.body(), JsonObject.class);
            return getStringOrEmpty(starshipsJsonObject, "name");
        } else {
            throw new RuntimeException("Failed to get starships data from SWAPI. HTTP status code: " + starshipsResponse.statusCode());
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
            long cost = getLongOrZero(starshipJsonObject, "cost_in_credits");
            String starshipClass = getStringOrEmpty(starshipJsonObject, "starship_class");

            return new StarWarsStarship(name, model, starshipClass, cost);
        } else {
            throw new RuntimeException("Failed to get starship data from SWAPI. HTTP status code: " + starshipResponse.statusCode());
        }
    }

    public StarWarsCharacter searchCharacterByName(String characterName) throws IOException, InterruptedException {
        String encodedCharacterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String searchURL = "https://swapi.dev/api/people/?search=" + encodedCharacterName;
        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .GET()
                .build();

        HttpResponse<String> searchResponse = client.send(searchRequest, HttpResponse.BodyHandlers.ofString());

        if (searchResponse.statusCode() == 200) {
            JsonObject searchResult = gson.fromJson(searchResponse.body(), JsonObject.class);

            if (searchResult.getAsJsonArray("results").size() > 0) {
                JsonObject characterJsonObject = searchResult.getAsJsonArray("results").get(0).getAsJsonObject();
                String characterId = characterJsonObject.get("url").getAsString().split("/")[5]; // Extrai o ID da URL
                return getCharacterData(characterId);
            } else {
                throw new RuntimeException("No character found with the name: " + characterName);
            }
        } else {
            throw new RuntimeException("Failed to search character by name from SWAPI. HTTP status code: " + searchResponse.statusCode());
        }
    }

    public StarWarsPlanets searchPlanetByName(String planetName) throws IOException, InterruptedException {
        String encodedPlanetName = URLEncoder.encode(planetName, StandardCharsets.UTF_8);
        String searchURL = "https://swapi.dev/api/planets/?search=" + encodedPlanetName;
        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .GET()
                .build();

        HttpResponse<String> searchResponse = client.send(searchRequest, HttpResponse.BodyHandlers.ofString());

        if (searchResponse.statusCode() == 200) {
            JsonObject searchResult = gson.fromJson(searchResponse.body(), JsonObject.class);

            if (searchResult.getAsJsonArray("results").size() > 0) {
                JsonObject planetJsonObject = searchResult.getAsJsonArray("results").get(0).getAsJsonObject();
                String planetId = planetJsonObject.get("url").getAsString().split("/")[5]; // Extrai o ID da URL
                return getPlanetData(planetId);
            } else {
                throw new RuntimeException("No planet found with the name: " + planetName);
            }
        } else {
            throw new RuntimeException("Failed to search planet by name from SWAPI. HTTP status code: " + searchResponse.statusCode());
        }
    }

    public StarWarsSpecies searchSpeciesByName(String speciesName) throws IOException, InterruptedException {
        String encodedSpeciesName = URLEncoder.encode(speciesName, StandardCharsets.UTF_8);
        String searchURL = "https://swapi.dev/api/species/?search=" + encodedSpeciesName;
        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .GET()
                .build();

        HttpResponse<String> searchResponse = client.send(searchRequest, HttpResponse.BodyHandlers.ofString());

        if (searchResponse.statusCode() == 200) {
            JsonObject searchResult = gson.fromJson(searchResponse.body(), JsonObject.class);

            if (searchResult.getAsJsonArray("results").size() > 0) {
                JsonObject speciesJsonObject = searchResult.getAsJsonArray("results").get(0).getAsJsonObject();
                String speciesId = speciesJsonObject.get("url").getAsString().split("/")[5]; // Extrai o ID da URL
                return getSpeciesDataById(speciesId);
            } else {
                throw new RuntimeException("No species found with the name: " + speciesName);
            }
        } else {
            throw new RuntimeException("Failed to search species by name from SWAPI. HTTP status code: " + searchResponse.statusCode());
        }
    }

    public StarWarsStarship searchStarshipByName(String starshipName) throws IOException, InterruptedException {
        String encodedStarshipName = URLEncoder.encode(starshipName, StandardCharsets.UTF_8);
        String searchURL = "https://swapi.dev/api/starships/?search=" + encodedStarshipName;
        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(URI.create(searchURL))
                .GET()
                .build();

        HttpResponse<String> searchResponse = client.send(searchRequest, HttpResponse.BodyHandlers.ofString());

        if (searchResponse.statusCode() == 200) {
            JsonObject searchResult = gson.fromJson(searchResponse.body(), JsonObject.class);

            if (searchResult.getAsJsonArray("results").size() > 0) {
                JsonObject starshipJsonObject = searchResult.getAsJsonArray("results").get(0).getAsJsonObject();
                String starshipId = starshipJsonObject.get("url").getAsString().split("/")[5]; // Extrai o ID da URL
                return getStarshipData(starshipId);
            } else {
                throw new RuntimeException("No starship found with the name: " + starshipName);
            }
        } else {
            throw new RuntimeException("Failed to search starship by name from SWAPI. HTTP status code: " + searchResponse.statusCode());
        }
    }

    public static void main(String[] args) {
        MainApplication service = new MainApplication();
        Scanner read = new Scanner(System.in);
        String searchType, searchInput;

        while (true) {
            // Limpa o console antes de cada busca
            service.clearConsole();

            System.out.println("Enter search type (character/planet/species/starship/exit):");
            searchType = read.nextLine();

            if (searchType.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println("Enter ID or Name to search:");
            searchInput = read.nextLine();

            try {
                switch (searchType.toLowerCase()) {
                    case "character":
                        if (searchInput.matches("\\d+")) {
                            StarWarsCharacter character = service.getCharacterData(searchInput);
                            service.displayCharacterData(character);
                        } else {
                            StarWarsCharacter character = service.searchCharacterByName(searchInput);
                            service.displayCharacterData(character);
                        }
                        break;
                    case "planet":
                        if (searchInput.matches("\\d+")) {
                            StarWarsPlanets planet = service.getPlanetData(searchInput);
                            service.displayPlanetData(planet);
                        } else {
                            StarWarsPlanets planet = service.searchPlanetByName(searchInput);
                            service.displayPlanetData(planet);
                        }
                        break;
                    case "species":
                        if (searchInput.matches("\\d+")) {
                            StarWarsSpecies species = service.getSpeciesDataById(searchInput);
                            service.displaySpeciesData(species);
                        } else {
                            StarWarsSpecies species = service.searchSpeciesByName(searchInput);
                            service.displaySpeciesData(species);
                        }
                        break;
                    case "starship":
                        if (searchInput.matches("\\d+")) {
                            StarWarsStarship starship = service.getStarshipData(searchInput);
                            service.displayStarshipData(starship);
                        } else {
                            StarWarsStarship starship = service.searchStarshipByName(searchInput);
                            service.displayStarshipData(starship);
                        }
                        break;
                    default:
                        System.out.println("Invalid search type. Try again.");
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("Press Enter to continue...");
            read.nextLine();
        }

        read.close();
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error clearing console: " + e.getMessage());
        }
    }

    private void displayCharacterData(StarWarsCharacter character) {
        System.out.println("Name: " + character.getName());
        System.out.println("Gender: " + character.getGender());
        System.out.println("Species: " + character.getSpecies());
        System.out.println("Vehicles: " + character.getVehicles());
        System.out.println("Starships: " + character.getStarships());
    }

    private void displayPlanetData(StarWarsPlanets planet) {
        System.out.println("Name: " + planet.getName());
        System.out.println("Population: " + planet.getPopulation());
        System.out.println("Terrain: " + planet.getTerrain());
        System.out.println("Climate: " + planet.getClimate());
    }

    private void displaySpeciesData(StarWarsSpecies species) {
        System.out.println("Name: " + species.getName());
        System.out.println("Classification: " + species.getClassification());
        System.out.println("Designation: " + species.getDesignation());
        System.out.println("Language: " + species.getLanguage());
    }

    private void displayStarshipData(StarWarsStarship starship) {
        System.out.println("Name: " + starship.getName());
        System.out.println("Model: " + starship.getModel());
        System.out.println("Starship Class: " + starship.getStarshipClass());
        System.out.println("Cost: " + starship.getCost());
    }
}
