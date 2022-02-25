package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    EditText editTextTextPersonName, editTextTextMultiLine;
    TextView listId;
    Note note;
    NoteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        Long id = i.getLongExtra("id",0);
        db = new NoteDatabase(this);
        note = db.getNote(id);




        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);

        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        listId = findViewById(R.id.listId);


        editTextTextPersonName.setText(note.getTitle());
        editTextTextMultiLine.setText(note.getContent());

        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setVisibility(View.VISIBLE);


        editTextTextPersonName.setSelection(editTextTextPersonName.length());
        editTextTextMultiLine.setSelection(editTextTextMultiLine.length());

        editTextTextPersonName.requestFocus();


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

                if (editTextTextPersonName.getText().length() != 0) {

                    note.setTitle(editTextTextPersonName.getText().toString());
                    note.setContent(editTextTextMultiLine.getText().toString());

                    int id = db.editNote(note);


                    Intent i = new Intent(view.getContext(),Details.class);
                    i.putExtra("id",note.getId());
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(view.getContext(),MainActivity.class);
                    view.getContext().startActivity(i);
                }
            }
        });

    }
}