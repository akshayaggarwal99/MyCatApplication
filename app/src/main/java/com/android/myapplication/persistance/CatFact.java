package com.android.myapplication.persistance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cat_fact_table")
public class CatFact implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "user_first_name")
    private String firstName;

    @ColumnInfo(name = "user_last_name")
    private String lastName;

    @ColumnInfo(name = "upvotes")
    private Integer upvotes;

    @ColumnInfo(name = "timestamp")
    private Integer timestamp;


    public CatFact(@NonNull String id, String text, String type, String firstName,
                   String lastName, Integer upvotes, Integer timestamp) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.upvotes = upvotes;
        this.timestamp = timestamp;
    }

    protected CatFact(Parcel in) {
        id = in.readString();
        text = in.readString();
        type = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            upvotes = null;
        } else {
            upvotes = in.readInt();
        }
        if (in.readByte() == 0) {
            timestamp = null;
        } else {
            timestamp = in.readInt();
        }
    }

    public static final Creator<CatFact> CREATOR = new Creator<CatFact>() {
        @Override
        public CatFact createFromParcel(Parcel in) {
            return new CatFact(in);
        }

        @Override
        public CatFact[] newArray(int size) {
            return new CatFact[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public static Creator<CatFact> getCREATOR() {
        return CREATOR;
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
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        if (upvotes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(upvotes);
        }
        if (timestamp == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(timestamp);
        }
    }
}
