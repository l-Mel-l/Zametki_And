package com.example.zametka;

public class Note {
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
        // Или, если вы хотите отобразить и заголовок и содержимое, то можно использовать:
        // return title + ": " + content;
    }
}
