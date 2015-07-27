package com.akiniyalocts.imgur_api.model;

import java.util.List;

/**
 * Created by anthony on 7/26/15.
 */
public class GalleryProfile {

    private int total_gallery_comments;
    private int total_gallery_favorites;
    private int total_gallery_submissions;
    private List<Trophy> trophies;

    public int getTotal_gallery_comments() {
        return total_gallery_comments;
    }

    public void setTotal_gallery_comments(int total_gallery_comments) {
        this.total_gallery_comments = total_gallery_comments;
    }

    public int getTotal_gallery_favorites() {
        return total_gallery_favorites;
    }

    public void setTotal_gallery_favorites(int total_gallery_favorites) {
        this.total_gallery_favorites = total_gallery_favorites;
    }

    public int getTotal_gallery_submissions() {
        return total_gallery_submissions;
    }

    public void setTotal_gallery_submissions(int total_gallery_submissions) {
        this.total_gallery_submissions = total_gallery_submissions;
    }

    public List<Trophy> getTrophies() {
        return trophies;
    }

    public void setTrophies(List<Trophy> trophies) {
        this.trophies = trophies;
    }
}
