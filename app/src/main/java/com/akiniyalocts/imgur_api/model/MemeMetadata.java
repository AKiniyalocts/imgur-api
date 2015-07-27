package com.akiniyalocts.imgur_api.model;

/**
 * Created by anthony on 7/26/15.
 */
public class MemeMetadata {
    private String meme_name;
    private String top_text;
    private String bottom_text;
    private String bg_image;

    public String getMeme_name() {
        return meme_name;
    }

    public void setMeme_name(String meme_name) {
        this.meme_name = meme_name;
    }

    public String getTop_text() {
        return top_text;
    }

    public void setTop_text(String top_text) {
        this.top_text = top_text;
    }

    public String getBottom_text() {
        return bottom_text;
    }

    public void setBottom_text(String bottom_text) {
        this.bottom_text = bottom_text;
    }

    public String getBg_image() {
        return bg_image;
    }

    public void setBg_image(String bg_image) {
        this.bg_image = bg_image;
    }
}
