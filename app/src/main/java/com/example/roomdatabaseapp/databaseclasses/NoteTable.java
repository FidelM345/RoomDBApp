package com.example.roomdatabaseapp.databaseclasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")//optional
public class NoteTable {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private  String content;


    //room uses constructor and getter methods to intsantiate the NOTETAble class

    public NoteTable(@NonNull String id, @NonNull String content) {
        this.id = id;
        this.content = content;
    }


    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getContent() {
        return content;
    }


}
