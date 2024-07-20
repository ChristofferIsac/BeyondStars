package com.starwars.BeyondStars.model;

public class StarWarsSpecies {
    private final String name;
    private final String classification;
    private final String designation;
    private final String language;

    public StarWarsSpecies(String name, String classification,String designation, String language){
        this.name = name;
        this.classification = classification;
        this.designation = designation;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getDesignation() {
        return designation;
    }

    public String getLanguage() {
        return language;
    }
}
