package com.unsober.data.responsedata;

import java.util.Date;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class ResponseItemDTO {
    private long id;
    private String title;
    private String description;
    private String image_link;
    private String youtube_link;
    private String number_of_players;
    private String tag1;
    private String tag2;
    private String tag3;
    private long category_id;
    private int status;
    private String datetime;
    private long views;

    public ResponseItemDTO(long id, String title, String description, String image_link, String youtube_link, String number_of_players, String tag1, String tag2, String tag3, long category_id, int status, String datetime, long views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image_link = image_link;
        this.youtube_link = youtube_link;
        this.number_of_players = number_of_players;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.category_id = category_id;
        this.status = status;
        this.datetime = datetime;
        this.views = views;
    }

    public ResponseItemDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public String getNumber_of_players() {
        return number_of_players;
    }

    public void setNumber_of_players(String number_of_players) {
        this.number_of_players = number_of_players;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
