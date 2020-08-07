package com.android.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserNameModel implements Parcelable {

    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;

    protected UserNameModel(Parcel in) {
        first = in.readString();
        last = in.readString();
    }

    public static final Creator<UserNameModel> CREATOR = new Creator<UserNameModel>() {
        @Override
        public UserNameModel createFromParcel(Parcel in) {
            return new UserNameModel(in);
        }

        @Override
        public UserNameModel[] newArray(int size) {
            return new UserNameModel[size];
        }
    };

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first);
        parcel.writeString(last);
    }
}
