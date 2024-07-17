package com.starwars.BeyondStars.model;

public class StarWarsCharacter {

    private String name;
    private String gender;
    private String species;
    private String affiliation;

    public StarWarsCharacter() {
    }

    public StarWarsCharacter(String name, String gender, String species, String affiliation) {
        this.name = name;
        this.gender = gender;
        this.species = species;
        this.affiliation = affiliation;
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
        return affiliation;
    }

}

