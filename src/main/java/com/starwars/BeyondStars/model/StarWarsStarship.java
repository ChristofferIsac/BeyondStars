package com.starwars.BeyondStars.model;

public class StarWarsStarship {
private final String name;
private final String model;
private final long cost;
private final String starshipClass;


public StarWarsStarship(String name, String model, String starshipClass, long cost) {
    this.name = name;
    this.model = model;
    this.cost = cost;
    this.starshipClass = starshipClass;
}

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public long getCost() {
        return cost;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

}
