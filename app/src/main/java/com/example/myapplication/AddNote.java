package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class AddNote extends AppCompatActivity {

    EditText editTextTextPersonName, editTextTextMultiLine;
    TextView listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        listId = findViewById(R.id.listId);


        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setVisibility(View.VISIBLE);

        editTextTextPersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                Note note = new Note(editTextTextPersonName.getText().toString(),editTextTextMultiLine.getText().toString());
                //NoteDatabase db = new NoteDatabase(AddNote.this);
                //db.addNote(note);

                NoteDatabase db = new NoteDatabase(AddNote.this);
                long id = db.addNote(note);
                Note check = db.getNote(id);

                Intent i = new Intent(view.getContext(),MainActivity.class);
                view.getContext().startActivity(i);



            }
        });

    }

}
