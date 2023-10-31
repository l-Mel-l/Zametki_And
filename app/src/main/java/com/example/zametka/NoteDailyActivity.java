package com.example.zametka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteDailyActivity extends AppCompatActivity {

    private EditText noteTitle;
    private EditText noteText;
    private int notePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_daily);

        noteTitle = findViewById(R.id.noteTitle);
        noteText = findViewById(R.id.noteText);
        Button backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("Тема");
            String text = intent.getStringExtra("Текст");
            noteTitle.setText(title);
            noteText.setText(text);
            notePosition = intent.getIntExtra("Position", -1);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedTitle = noteTitle.getText().toString();
                String updatedText = noteText.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("Тема", updatedTitle);
                returnIntent.putExtra("Текст", updatedText);
                returnIntent.putExtra("Position", notePosition);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}