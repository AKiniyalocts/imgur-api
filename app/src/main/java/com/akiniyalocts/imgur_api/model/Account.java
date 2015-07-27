package com.akiniyalocts.imgur_api.model;

/**
 * Created by anthony on 7/26/15.
 */
public class Account {
    private int id;
    private String url;
    private String bio;
    private float reputation;
    private int created;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getReputation() {
        return reputation;
    }

    public void setReputation(float reputation) {
        this.reputation = reputation;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }
}
