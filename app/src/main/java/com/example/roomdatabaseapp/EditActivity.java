package com.example.roomdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabaseapp.databaseclasses.EditNoteViewModel;
import com.example.roomdatabaseapp.databaseclasses.NoteTable;

public class EditActivity extends AppCompatActivity {
    EditText editText;
    Button update, cancel;
    String noteId;
    public static final String NOTE_ID ="note_id" ;
    public static final String NOTE_UPDATED ="note_update" ;
    LiveData<NoteTable>noteTableLiveData;

            EditNoteViewModel editNoteViewModel;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_edit);

                editText=findViewById(R.id.id_edit_input);
                update=findViewById(R.id.update_btn);
                cancel=findViewById(R.id.cancel_btn);


                noteId = getIntent().getExtras().getString("note_id");


                Log.i("thebeast1", "The note_id is: "+noteId);


                editNoteViewModel= ViewModelProviders.of(this).get(EditNoteViewModel.class);

                //retrieves data with the given id from the database
                noteTableLiveData=editNoteViewModel.getNoteTable(noteId);


                noteTableLiveData.observe(this, new Observer<NoteTable>() {
                    @Override
                    public void onChanged(NoteTable noteTable) {

                        Log.i("thebeast", "The data is: "+noteTable.getContent());

                        editText.setText(noteTable.getContent());

                    }

                });


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateNote();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelUpdate();
                    }
                });


            }



            public void updateNote(){

                Intent resultintent=new Intent();

                String note=editText.getText().toString().trim();
                resultintent.putExtra(NOTE_ID,noteId);
                resultintent.putExtra(NOTE_UPDATED,note);
                setResult(RESULT_OK,resultintent);

                finish();

            }


            public void cancelUpdate(){
                finish();

            }




}
