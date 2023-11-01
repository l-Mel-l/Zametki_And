package com.example.zametka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteEditActivity extends AppCompatActivity {

    private int position = -1;

    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        if (position != -1) {
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            titleEditText.setText(title);
            contentEditText.setText(content);
        }
    }

    public void onSaveNoteClick(View view) {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        Intent data = new Intent();
        data.putExtra("position", position);
        data.putExtra("title", title);
        data.putExtra("content", content);
        setResult(RESULT_OK, data);
        finish();
    }
}