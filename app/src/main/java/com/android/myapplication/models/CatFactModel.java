package com.android.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatFactModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("user")
    @Expose
    private UserModel user;
    @SerializedName("upvotes")
    @Expose
    private Integer upvotes;
    @SerializedName("userUpvoted")
    @Expose
    private Object userUpvoted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Object getUserUpvoted() {
        return userUpvoted;
    }

    public void setUserUpvoted(Object userUpvoted) {
        this.userUpvoted = userUpvoted;
    }
}
