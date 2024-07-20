package com.starwars.BeyondStars.model;

public class StarWarsPlanets {
    private final String name;
    private final long population;
    private final String terrain;
    private final String climate;



    public StarWarsPlanets(String name, long population, String terrain, String climate) {
        this.name = name;
        this.population = population;
        this.terrain = terrain;
        this.climate = climate;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getClimate() {
        return climate;
    }
}
