package com.example.zametka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notes = new ArrayList<>(); //Список заметок
    private ArrayAdapter<Note> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNoteButton = findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(new View.OnClickListener() { //слушатель для кнопки "Добавить заметку", который вызываетм метод onAddNoteClick
            @Override
            public void onClick(View v) {
                onAddNoteClick(v);
            }
        });

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {                    //Устанавливается слушатель для элементов списка.
            @Override                                                                              //Когда пользователь нажимает на элемент списка, создается новый объект Intent, который содержит информацию о позиции элемента в списке, его заголовке и содержимом.
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);  //ЗАпускается NoteEditActivity
                intent.putExtra("position", position);
                intent.putExtra("title", notes.get(position).getTitle());
                intent.putExtra("content", notes.get(position).getContent());
                startActivityForResult(intent, 1);
            }
        });
    }


    public void onAddNoteClick(View view) {                                        //метод вызывается при нажатии на кнопку "Добавить заметку"
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {      // метод вызывается, когда NoteEditActivity завершает свою работу и возвращает результат
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {                               // Если результат успешен, он получает данные из Intent и обновляет список заметок
            int position = data.getIntExtra("position", -1);
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");

            if (position == -1) {
                notes.add(new Note(title, content));
            } else {
                notes.get(position).setTitle(title);
                notes.get(position).setContent(content);
            }

            adapter.notifyDataSetChanged();
        }
    }
}