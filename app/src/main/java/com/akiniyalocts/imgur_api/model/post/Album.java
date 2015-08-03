package com.akiniyalocts.imgur_api.model.post;

import com.akiniyalocts.imgur_api.model.enums.AlbumLayout;
import com.akiniyalocts.imgur_api.model.enums.AlbumPrivacy;

import java.util.Arrays;

/**
 * Used for creating and updating an album
 */
public class Album {

    public Album() {
    }

    /**
     * Converts existing Album object to a post album, so it can be used to update an album
     *
     * @param album album which will be converted
     */
    public Album(com.akiniyalocts.imgur_api.model.Album album) {
        imageIds = album.getImageIds();
        title = album.getTitle();
        description = album.getDescription();
        albumPrivacy = album.getPrivacyEnum();
        layout = album.getLayoutEnum();
        cover = album.getCover();
    }

    /**
     * Image Ids which will be initially added to the album
     */
    private String[] imageIds;

    /**
     * Title
     */
    private String title;

    /**
     * Description
     */
    private String description;

    /**
     * Privacy setting
     */
    private AlbumPrivacy albumPrivacy;

    /**
     * Layout
     */
    private AlbumLayout layout;

    /**
     * Cover image
     */
    private String cover;

    @Override
    public String toString() {
        return "Album{" +
                "imageIds=" + Arrays.toString(imageIds) +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", albumPrivacy=" + albumPrivacy +
                ", layout=" + layout +
                ", cover=" + cover +
                '}';
    }

    public String[] getImageIds() {
        return imageIds;
    }

    /**
     * Image Ids for the album
     *
     * @param imageIds Image Ids which will be initially added to the album
     */
    public void setImageIds(String[] imageIds) {
        this.imageIds = imageIds;
    }

    public String getTitle() {
        return title;
    }

    /**
     * @param title Title of the album
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param description Description of the album
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public AlbumPrivacy getAlbumPrivacy() {
        return albumPrivacy;
    }

    /**
     * Set Album Privacy Setting
     *
     * @param albumPrivacy Privacy setting for the album
     */
    public void setAlbumPrivacy(AlbumPrivacy albumPrivacy) {
        this.albumPrivacy = albumPrivacy;
    }

    public AlbumLayout getLayout() {
        return layout;
    }

    /**
     * Set Album layout
     *
     * @param layout Layout of the album
     */
    public void setLayout(AlbumLayout layout) {
        this.layout = layout;
    }

    public String getCover() {
        return cover;
    }

    /**
     * Set cover image
     *
     * @param cover Cover image id
     */
    public void setCover(String cover) {
        this.cover = cover;
    }
}