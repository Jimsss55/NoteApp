package com.example.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnItemCLickListener{

    Button addnote;
    ArrayList<Notes> noteList = new ArrayList<>();
    NoteAdapter noteAdapter;
    RecyclerView recyclerView;
    private static final int EDIT_NOTE_REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addnote=findViewById(R.id.addnoteid);

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(intent,2);
            }
        });

        //For Recycler View
        recyclerView=findViewById(R.id.recycleid);

        noteAdapter=new NoteAdapter(this, noteList,this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    // Handle the result from AddNoteActivity or EditNoteActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        This handles the result from the AddNote Activity
                if (requestCode == 2) {
                String noteTitleText = data.getStringExtra("title");
                String noteBody = data.getStringExtra("body");
                setupNoteModel(noteTitleText, noteBody);
//                This handles the note from the EditNote Activity
        } else if (requestCode == EDIT_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
                    String editedTitle = data.getStringExtra("editedTitle");
                    String editedBody = data.getStringExtra("editedDes");
                    int position = data.getIntExtra("position", -1);

                    if (position != -1) {
                        noteList.get(position).setTitle(editedTitle);
                        noteList.get(position).setDescription(editedBody);
                        noteAdapter.notifyItemChanged(position);
                }
        }
    }

    // Add a new note to the list and update the RecyclerView
    public void setupNoteModel(String noteTitleText, String noteBody){
        noteList.add(new Notes(noteTitleText,noteBody));
        noteAdapter.notifyDataSetChanged();
    }

    // Handle the edit button click in the RecyclerView
    @Override
    public void onEditClick(int position) {
        // Handle edit click here
        Intent intent=new Intent(this,EditNoteActivity.class);
        intent.putExtra("title",noteList.get(position).getTitle());
        intent.putExtra("body",noteList.get(position).getDescription());
        startActivityForResult(intent,EDIT_NOTE_REQUEST_CODE);
    }


    // Handle the delete button click in the RecyclerView
    @Override
    public void onDeleteClick(int position) {
        // Handle delete click here
        noteList.remove(position);
        noteAdapter.notifyDataSetChanged();
    }
}