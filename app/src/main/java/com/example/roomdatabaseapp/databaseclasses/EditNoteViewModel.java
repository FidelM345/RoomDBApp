package com.example.roomdatabaseapp.databaseclasses;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class EditNoteViewModel extends AndroidViewModel {

    private final static String TAG="thebeast";
    NoteDataBases db;
    NoteDAO noteDAO;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "EditNoteViewModel: ");
        db=NoteDataBases.getNoteDataBases(application);
        noteDAO=db.noteDAO();
    }


    //wrapper function for the get note method from the NoteDAO
    //it retrieves note with the given id from the database
    public LiveData<NoteTable> getNoteTable(String noteId){
        return noteDAO.getNote(noteId);
    }



}
