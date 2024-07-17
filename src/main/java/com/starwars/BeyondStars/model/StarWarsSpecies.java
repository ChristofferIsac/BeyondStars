package com.starwars.BeyondStars.model;

public class StarWarsSpecies {
    private String name;
    private String classification;
    private String designation;
    private String language;

    public StarWarsSpecies(String name, String classification, String language){
    }

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
