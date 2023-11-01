package com.example.zametka;

public class Note {                       //класс представляет объект заметки. Он содержит поля для заголовка и содержимого заметки
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return title; // Вернуть только заголовок заметки
    }
}
