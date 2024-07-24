package com.starwars.BeyondStars.model;

public class StarWarsCharacter {

    private String name;
    private String gender;
    private String species;
    private String vehicles;
    private String starships;

    public StarWarsCharacter() {
    }

    public StarWarsCharacter(String name, String gender, String species, String vehicles, String starships) {
        this.name = name;
        this.gender = gender;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public String getAffiliation() {
        return vehicles;
    }

    public String getStarships() {
        return starships;
    }

    public String getVehicles() {
        return vehicles;
    }
}

