package com.gacha.quizapp.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Category implements Serializable {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category(String category) {
        this.category = category;
    }
    public Category() {
    }
    @NonNull
    @Override
    public String toString(){
        return category;
    }
}
