package com.android.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private UserNameModel userNameModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNameModel getUserNameModel() {
        return userNameModel;
    }

    public void setUserNameModel(UserNameModel userNameModel) {
        this.userNameModel = userNameModel;
    }
}
