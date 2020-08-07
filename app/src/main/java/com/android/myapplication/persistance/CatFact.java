package com.android.myapplication.persistance;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cat_fact_table")
public class CatFact {

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


    public CatFact(@NonNull String id, String text, String type, String firstName, String lastName, Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.upvotes = upvotes;
    }


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
}
