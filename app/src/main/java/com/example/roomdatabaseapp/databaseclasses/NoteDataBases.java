package com.example.roomdatabaseapp.databaseclasses;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//list of entitites or tables associated with the databse
@Database(entities = NoteTable.class,version = 1,exportSchema = false)
public abstract class NoteDataBases extends RoomDatabase {
    //  A Java abstract class is a class which cannot be instantiated, meaning you cannot create new instances of an abstract class.
    //they are meant to act as base for subclasses. meaning you extend them an implement the methods

    //the Dm class must have an abstract method of all DAO related to it
    public abstract NoteDAO noteDAO();

    //adding single to to ensure we only have a single instance of our database

    private static volatile NoteDataBases noteDataBasesInstance;

    static NoteDataBases getNoteDataBases(final Context context) {

        if(noteDataBasesInstance==null){

            synchronized (NoteDataBases.class){
                //the roo instance is null it is created here
                if(noteDataBasesInstance==null){
                  //we pass the application context, class context and name of database
                    noteDataBasesInstance= Room.databaseBuilder(context.getApplicationContext(),
                            NoteDataBases.class,"note_database")
                            .build();
                }

            }

        }

        return noteDataBasesInstance;
    }


}


