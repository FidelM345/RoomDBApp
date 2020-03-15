package com.example.roomdatabaseapp.databaseclasses;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(NoteTable noteTable);

    //the live data functionality ensures our recyclerview is updated on realtime
    @Query("SELECT * FROM notes")
    LiveData<List<NoteTable>>getAllNotes();

    @Query("SELECT * FROM notes WHERE id=:noteId")
    LiveData<NoteTable>getNote(String noteId);

    @Update
    void update(NoteTable noteTable);

    @Delete
    void  delete(NoteTable noteTable);





}
