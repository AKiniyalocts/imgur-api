package com.akiniyalocts.imgur_api.model;

import java.util.List;

/**
 * Created by anthony on 7/26/15.
 */
public class Conversation {
    private int id;
    private String last_message_preview;
    private int datetime;
    private int with_account_id;
    private String with_account;
    private int message_count;
    private List<Message> messages;
    private boolean done;
    private int page;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_message_preview() {
        return last_message_preview;
    }

    public void setLast_message_preview(String last_message_preview) {
        this.last_message_preview = last_message_preview;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public int getWith_account_id() {
        return with_account_id;
    }

    public void setWith_account_id(int with_account_id) {
        this.with_account_id = with_account_id;
    }

    public String getWith_account() {
        return with_account;
    }

    public void setWith_account(String with_account) {
        this.with_account = with_account;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
