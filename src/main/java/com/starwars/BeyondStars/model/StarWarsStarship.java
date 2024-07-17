package com.starwars.BeyondStars.model;

public class StarWarsStarship {
private String name;
private String model;
private int cost;
private String starshipClass;

public StarWarsStarship(String name, String model, int cost) {

}

public StarWarsStarship(String name, String model, int cost, String starshipClass) {
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

    public int getCost() {
        return cost;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

}
