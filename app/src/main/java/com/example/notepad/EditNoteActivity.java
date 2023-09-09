package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    Button save_edit;
    EditText edittitle,editdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);


        edittitle=findViewById(R.id.edittitleid);
        editdes=findViewById(R.id.editdescid);
        save_edit=findViewById(R.id.editsave);

        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("body");

        edittitle.setText(title);
        editdes.setText(description);

        save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gettitle=edittitle.getText().toString();
                String getdes=editdes.getText().toString();

                Intent intent=new Intent();
                intent.putExtra("editedTitle",gettitle);
                intent.putExtra("editedDes",getdes);
                int position = getIntent().getIntExtra("position", -1);
                intent.putExtra("position", position);

                setResult(RESULT_OK, intent);
                finish();
//                setResult(2,intent);
//                finish();
            }
        });

    }
}