package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView detailsTitle, detailsDescription;
    NoteDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsTitle = findViewById(R.id.detailsTitle);
        detailsDescription = findViewById(R.id.detailsDescription);

        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button editBtn = findViewById(R.id.editBtn);

        Intent i = getIntent();
        Long id = i.getLongExtra("id",0);

        db = new NoteDatabase(this);
        note = db.getNote(id);

        detailsTitle.setText(note.getTitle());
        detailsDescription.setText(note.getContent());

        detailsDescription.setMovementMethod(new ScrollingMovementMethod());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getId());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}