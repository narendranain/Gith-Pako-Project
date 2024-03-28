package com.azhar.peko.model.repo;

import com.google.gson.annotations.SerializedName;

public class RepositoryDataCap {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("stargazers_count")
    private int starsCount;

    @SerializedName("forks_count")
    private int forksCount;
    @SerializedName("html_url")
    private String proj_url;


    // Add more fields as needed

    // Constructor, getters, and setters
    public RepositoryDataCap(String name, String description, String language, int starsCount, int forksCount, String proj_url) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.starsCount = starsCount;
        this.forksCount = forksCount;
        this.proj_url = proj_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getProj_url() {
        return proj_url;
    }
}
