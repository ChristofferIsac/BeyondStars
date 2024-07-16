package com.starwars.BeyondStars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.starwars.BeyondStars.model.StarWarsCharacter;

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
        String characterID = "https://swapi.dev/api/people/" + characterId + "/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(characterID))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            String name = jsonObject.get("name").getAsString();
            String gender = jsonObject.get("gender").getAsString();
            String species = jsonObject.has("species") && jsonObject.get("species").isJsonArray() && jsonObject.getAsJsonArray("species").size() > 0
                    ? jsonObject.getAsJsonArray("species").get(0).getAsString()
                    : "unknown";
            String affiliation = "unknown";  // Adjust this based on your data model

            return new StarWarsCharacter(name, gender, species, affiliation);
        } else {
            throw new RuntimeException("Failed to get character data from SWAPI");
        }
    }

    public static void main(String[] args) {
        SwapiService service = new SwapiService();
        Scanner read = new Scanner(System.in);
        String characterSearch = "";

        while (!characterSearch.equalsIgnoreCase("May the Force Be With You")) {
            System.out.println("Type a character ID:");
            characterSearch = read.nextLine();

            if (characterSearch.equalsIgnoreCase("May the Force Be With You")) {
                System.out.println("May the Force Be With You, Old Friend!");
                break;
            }

            System.out.println("Looking for the character number " + characterSearch + "...");
            try {
                StarWarsCharacter character = service.getCharacterData(characterSearch);
                System.out.println("Name: " + character.getName());
                System.out.println("Gender: " + character.getGender());
                System.out.println("Species: " + character.getSpecies());
                System.out.println("Affiliation: " + character.getAffiliation());
            } catch (IOException | InterruptedException e) {
                System.err.println("Error occurred while calling SWAPI: " + e.getMessage());
            }
        }

        read.close();
    }
}



