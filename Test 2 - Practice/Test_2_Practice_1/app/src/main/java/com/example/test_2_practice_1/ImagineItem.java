package com.example.test_2_practice_1;

import android.graphics.Bitmap;

public class ImagineItem {
    private String text;
    private Bitmap imagine;
    private String link;

    public ImagineItem(String text, Bitmap imagine, String link) {
        this.text = text;
        this.imagine = imagine;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImagineItem{" +
                "text='" + text + '\'' +
                ", imagine=" + imagine +
                ", link='" + link + '\'' +
                '}';
    }
}
