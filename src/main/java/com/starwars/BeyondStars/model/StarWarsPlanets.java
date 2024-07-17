package com.starwars.BeyondStars.model;

public class StarWarsPlanets {
    private String name;
    private int population;
    private String terrain;
    private String climate;

    public StarWarsPlanets() {
    }

    public StarWarsPlanets(String name, int population, String terrain, String climate) {
        this.name = name;
        this.population = population;
        this.terrain = terrain;
        this.climate = climate;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getClimate() {
        return climate;
    }
}
