package com.akiniyalocts.imgur_api.model;

/**
 * Created by anthony on 7/26/15.
 */
public class TagVote {
    private int ups;
    private int downs;
    private String string;
    private String author;

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
