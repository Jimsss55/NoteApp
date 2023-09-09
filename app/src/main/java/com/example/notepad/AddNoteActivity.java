package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    EditText title,description;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title=findViewById(R.id.titleid);
        description=findViewById(R.id.descid);
        save=findViewById(R.id.savebtnid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title_save=title.getText().toString();
                String body=description.getText().toString();

                Intent intent=new Intent();
                intent.putExtra("title",title_save);
                intent.putExtra("body",body);
                setResult(2,intent);
                finish();
            }
        });

    }
}