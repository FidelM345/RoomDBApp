package com.example.roomdatabaseapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomdatabaseapp.adapters.ViewNotes_Adapter;
import com.example.roomdatabaseapp.databaseclasses.AddNote;
import com.example.roomdatabaseapp.databaseclasses.NoteTable;
import com.example.roomdatabaseapp.databaseclasses.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ViewNotes_Adapter.onDeleteInterface {

    private static final int NEW_NOTE_ACTIVITY_EQQUEST_CODE =1;
    public static final int UPDATE_NOTE_ACTIVITY_EQQUEST_CODE =2;
    private static final String TAG = "thebeast";
    private NoteViewModel noteViewModel;

    RecyclerView recyclerView;
    ViewNotes_Adapter viewNotes_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        recyclerView=findViewById(R.id.id_recycler);
        viewNotes_adapter=new ViewNotes_Adapter(MainActivity.this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(viewNotes_adapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, AddNote.class);
                startActivityForResult(intent,NEW_NOTE_ACTIVITY_EQQUEST_CODE);
            }
        });


        //instantiates the view models class
        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteTable>>() {
            @Override
            public void onChanged(List<NoteTable> noteTableList) {

                viewNotes_adapter.setNotes(noteTableList);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


           if(requestCode==NEW_NOTE_ACTIVITY_EQQUEST_CODE&& resultCode==RESULT_OK){


               //code for generating random ids
               String note_id= UUID.randomUUID().toString();
               NoteTable noteTable=new NoteTable(note_id,data.getStringExtra(AddNote.NOTE_ADDED));
               noteViewModel.insert(noteTable);

               Log.i(TAG, "onActivityResult: "+"data has been successfully saved");

               Toast.makeText(this, "data has been successfully saved", Toast.LENGTH_SHORT).show();


           }else if(requestCode==UPDATE_NOTE_ACTIVITY_EQQUEST_CODE&& resultCode==RESULT_OK){

               NoteTable noteTable=new NoteTable(data.getStringExtra(EditActivity.NOTE_ID),data.getStringExtra(EditActivity.NOTE_UPDATED));
               noteViewModel.update(noteTable);

               Toast.makeText(this, "data updated successfully", Toast.LENGTH_SHORT).show();



           }else{

               Toast.makeText(this, "data saving failed", Toast.LENGTH_SHORT).show();

               Log.i(TAG, "onActivityResult: "+"data saving failed");


           }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteClickListener(NoteTable noteTable) {
        //code for delete operation

        //the object noteTable is passed from the ViewNotes_Adapter in the interface
         noteViewModel.delete(noteTable);

    }
}
