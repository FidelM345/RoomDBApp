package com.example.roomdatabaseapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseapp.EditActivity;
import com.example.roomdatabaseapp.MainActivity;
import com.example.roomdatabaseapp.R;
import com.example.roomdatabaseapp.databaseclasses.NoteTable;

import java.util.ArrayList;
import java.util.List;

public class ViewNotes_Adapter extends RecyclerView.Adapter<ViewNotes_Adapter.ViewHolder>{


    public  interface  onDeleteInterface{

        void onDeleteClickListener(NoteTable noteTable);



    }

    private Context context;
    //necessary for intiallizing the array list and avoiding null pointer exception
    private List<NoteTable>noteTableList=new ArrayList<>();

    private onDeleteInterface onDeleteInterface;




    public ViewNotes_Adapter(Context context, onDeleteInterface onDeleteInterface) {

        this.context=context;
        this.onDeleteInterface=onDeleteInterface;
    }

    @NonNull
    @Override
    public ViewNotes_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //it inflates the custom made Layout file for list items
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
      //  context=parent.getContext();


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNotes_Adapter.ViewHolder holder, int position) {
        if(noteTableList!=null){

            NoteTable noteTable=noteTableList.get(position);
            holder.setData(noteTable.getContent(),position);

            holder.setListener();
        }else{

            holder.textView.setText("No data is available");
        }



    }

    public void setNotes(List<NoteTable>noteTableList){
        this.noteTableList=noteTableList;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        //number of items to be populated in the recycler view
        if(!noteTableList.isEmpty()){


            return noteTableList.size();

        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView delete,edit;


        int mposition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_text);
            delete=itemView.findViewById(R.id.id_delete);
            edit=itemView.findViewById(R.id.id_edit);
        }


        public void setData(String content, int position) {

            textView.setText(content);
            mposition=position;

        }

        public void setListener() {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onDeleteInterface!=null){

                        onDeleteInterface.onDeleteClickListener(noteTableList.get(mposition));

                    }

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, EditActivity.class);
                    intent.putExtra("note_id",noteTableList.get(mposition).getId());
                    //we use this method becoz we will be returning to the main activity after editing our data
                    ((Activity)context).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_EQQUEST_CODE);

                }
            });


        }
    }
}
