package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.w3c.dom.Notation;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout NoteOrChecklist = (LinearLayout) findViewById(R.id.NoteorChecklist);
        NoteOrChecklist.setVisibility(View.GONE);
        Button bgbtn = (Button) findViewById(R.id.button6);
        Button noteBtn = (Button) findViewById(R.id.button5);
        bgbtn.setVisibility(View.GONE);



        NoteDatabase db =  new NoteDatabase(this);
        notes = db.getNotes();
        recyclerView = findViewById(R.id.listOfNotes);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new Adapter(this, notes);
        recyclerView.setAdapter(adapter);

        Button add = (Button) findViewById(R.id.button4);



        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //NoteOrChecklist.setVisibility(View.VISIBLE);
                //bgbtn.setVisibility(View.VISIBLE);

                Intent i = new Intent(view.getContext(),AddNote.class);
                view.getContext().startActivity(i);
            }

        });
        bgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteOrChecklist.setVisibility(View.GONE);
                bgbtn.setVisibility(View.GONE);
            }
        });
        noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(),AddNote.class);
                view.getContext().startActivity(i);
            }
        });





    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        moveTaskToBack(false);
        return true;
    }

}