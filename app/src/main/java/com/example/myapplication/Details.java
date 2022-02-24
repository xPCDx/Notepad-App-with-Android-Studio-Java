package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView detailsTitle, detailsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsTitle = findViewById(R.id.detailsTitle);
        detailsDescription = findViewById(R.id.detailsDescription);


        Intent i = getIntent();
        Long id = i.getLongExtra("id",0);

        NoteDatabase db = new NoteDatabase(this);
        Note note = db.getNote(id);

        detailsTitle.setText(note.getContent());
        detailsDescription.setText(note.getContent());



    }
}