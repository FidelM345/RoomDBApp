package com.example.roomdatabaseapp.databaseclasses;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.example.roomdatabaseapp.databaseclasses.NoteDAO;
import com.example.roomdatabaseapp.databaseclasses.NoteDataBases;
import com.example.roomdatabaseapp.databaseclasses.NoteTable;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {
    private NoteDAO noteDAO;
    private NoteDataBases noteDataBases;
    private LiveData<List<NoteTable>>allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);

        //we used the AndroidViewModel inorder to pass the application context with
        // android viewModel does not allow us
        //intsantiating the DAO and the DB
        noteDataBases=NoteDataBases.getNoteDataBases(application);
        noteDAO=noteDataBases.noteDAO();
        //using live data we do need to use async task because the task will be automatically performed in background
        allNotes=noteDAO.getAllNotes();//intializing



    }

    //creating wrapper for insert operation
    public void insert(NoteTable noteTable){
        
        new InsertAsyncTask(noteDAO).execute(noteTable);


    }


    //creating wrapper for insert operation
    public void update(NoteTable noteTable){

        new UpdateAsyncTask(noteDAO).execute(noteTable);


    }


    //creating wrapper for delete operation
    public void delete(NoteTable noteTable){

        new DeleteAsyncTask(noteDAO).execute(noteTable);


    }


    //creating wrapper for getallNotes operation
     public LiveData<List<NoteTable>> getAllNotes(){

        return allNotes;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class InsertAsyncTask extends AsyncTask<NoteTable,Void,Void> {
        //async task performs the operation in a background thread
        public InsertAsyncTask(NoteDAO noteDAO) {
        }

        @Override
        protected Void doInBackground(NoteTable... noteTables) {
            noteDAO.insert(noteTables[0]);
            return null;

        }
    }

    private class UpdateAsyncTask  extends AsyncTask<NoteTable,Void,Void> {

        NoteDAO noteDAO;

        public UpdateAsyncTask(NoteDAO noteDAO) {
            this.noteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(NoteTable... noteTables) {
            noteDAO.update(noteTables[0]);
            return null;
        }
    }

    private class DeleteAsyncTask  extends AsyncTask<NoteTable,Void,Void> {

        NoteDAO noteDAO;

        public DeleteAsyncTask(NoteDAO noteDAO) {

            this.noteDAO=noteDAO;

        }

        @Override
        protected Void doInBackground(NoteTable... noteTables) {

            noteDAO.delete(noteTables[0]);
            return null;
        }
    }
}
