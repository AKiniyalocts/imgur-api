package com.akiniyalocts.imgur_api.model;

/**
 * Created by anthony on 7/26/15.
 */
public class AccountSettings{
    private String email;
    private boolean high_quality;
    private boolean public_images;
    private String album_privacy;
    private boolean accepted_gallery_terms;
    private String[] active_emails;
    private boolean messaging_enabled;
    private Object blocked_users;
    private boolean show_mature;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHigh_quality() {
        return high_quality;
    }

    public void setHigh_quality(boolean high_quality) {
        this.high_quality = high_quality;
    }

    public boolean isPublic_images() {
        return public_images;
    }

    public void setPublic_images(boolean public_images) {
        this.public_images = public_images;
    }

    public String getAlbum_privacy() {
        return album_privacy;
    }

    public void setAlbum_privacy(String album_privacy) {
        this.album_privacy = album_privacy;
    }

    public boolean isAccepted_gallery_terms() {
        return accepted_gallery_terms;
    }

    public void setAccepted_gallery_terms(boolean accepted_gallery_terms) {
        this.accepted_gallery_terms = accepted_gallery_terms;
    }

    public String[] getActive_emails() {
        return active_emails;
    }

    public void setActive_emails(String[] active_emails) {
        this.active_emails = active_emails;
    }

    public boolean isMessaging_enabled() {
        return messaging_enabled;
    }

    public void setMessaging_enabled(boolean messaging_enabled) {
        this.messaging_enabled = messaging_enabled;
    }

    public Object getBlocked_users() {
        return blocked_users;
    }

    public void setBlocked_users(Object blocked_users) {
        this.blocked_users = blocked_users;
    }

    public boolean isShow_mature() {
        return show_mature;
    }

    public void setShow_mature(boolean show_mature) {
        this.show_mature = show_mature;
    }
}
