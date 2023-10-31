package com.example.zametka;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView noteListView;
    private ArrayAdapter<String> adapter;

    private String[] notes = {"Заметка 1", "Заметка 2", "Заметка 3"};

    private ActivityResultLauncher<Intent> noteLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String updatedTitle = data.getStringExtra("Тема");
                        String updatedText = data.getStringExtra("Текст");
                        int position = data.getIntExtra("Position", -1);
                        if (position != -1) {
                            notes[position] = updatedTitle;
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteListView = findViewById(R.id.noteListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        noteListView.setAdapter(adapter);

        for (int i = 0; i < notes.length; i++) {
            String savedText = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("NoteText_" + i, "");
            if (!savedText.equals("")) {
                notes[i] = savedText;
            }
        }

        noteListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, NoteDailyActivity.class);
            intent.putExtra("Тема", notes[position]);
            intent.putExtra("Текст", ((TextView) view).getText().toString());
            intent.putExtra("Position", position);
            noteLauncher.launch(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (int i = 0; i < notes.length; i++) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
            editor.putString("NoteText_" + i, notes[i]);
            editor.apply();
        }
    }
}