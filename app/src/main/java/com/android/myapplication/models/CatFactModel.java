package com.android.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class CatFactModel implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("user")
    @Expose @Nullable
    private UserModel user;
    @SerializedName("upvotes")
    @Expose
    private Integer upvotes;


    protected CatFactModel(Parcel in) {
        _id = in.readString();
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
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
