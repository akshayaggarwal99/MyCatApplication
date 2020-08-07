package com.android.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatFactModel implements Parcelable {

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

    protected CatFactModel(Parcel in) {
        id = in.readString();
        text = in.readString();
        type = in.readString();
        if (in.readByte() == 0) {
            upvotes = null;
        } else {
            upvotes = in.readInt();
        }
    }

    public static final Creator<CatFactModel> CREATOR = new Creator<CatFactModel>() {
        @Override
        public CatFactModel createFromParcel(Parcel in) {
            return new CatFactModel(in);
        }

        @Override
        public CatFactModel[] newArray(int size) {
            return new CatFactModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(text);
        parcel.writeString(type);
        if (upvotes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(upvotes);
        }
    }
}
