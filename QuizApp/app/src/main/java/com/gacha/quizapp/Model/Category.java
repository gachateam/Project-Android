package com.gacha.quizapp.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Category implements Serializable {
    private String category;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category(int id,String category) {
        this.category = category;
        this.id = id;
    }
    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", id=" + id +
                '}';
    }
}
