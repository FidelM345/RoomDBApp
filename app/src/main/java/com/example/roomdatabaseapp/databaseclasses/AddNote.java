package com.example.roomdatabaseapp.databaseclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabaseapp.R;

public class AddNote extends AppCompatActivity {
    public static final String NOTE_ADDED ="note_text" ;
    EditText data;
    Button button;
    Intent resultintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        data=findViewById(R.id.id_data);
        button=findViewById(R.id.id_btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultintent=new Intent();

                if(TextUtils.isEmpty(data.getText())){

                    setResult(RESULT_CANCELED,resultintent);

                }else{

                    String note=data.getText().toString().trim();
                    resultintent.putExtra(NOTE_ADDED,note);
                    setResult(RESULT_OK,resultintent);

                }

                finish();



            }
        });
    }
}
