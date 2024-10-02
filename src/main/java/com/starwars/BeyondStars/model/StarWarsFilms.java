package com.starwars.BeyondStars.model;

public class StarWarsFilms {
    private String title;
    private String episode;
    private String characters;
    private String planets;
    private String starships;


    public StarWarsFilms() {
    }

    public StarWarsFilms(String title, String episode, String characters, String planets, String starships) {
        this.title = title;
        this.episode = episode;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
    }

    // Getters e setters (opcional)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getPlanets() {
        return planets;
    }

    public void setPlanets(String planets) {
        this.planets = planets;
    }

    public String getStarships() {
        return starships;
    }

    public void setStarships(String starships) {
        this.starships = starships;
    }
}
