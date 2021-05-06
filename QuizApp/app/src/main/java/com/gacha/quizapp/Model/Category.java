package com.gacha.quizapp.Model;

public class Category {
    private String category;
    private int color;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Category(String category, int color) {
        this.category = category;
        this.color = color;
    }
}
