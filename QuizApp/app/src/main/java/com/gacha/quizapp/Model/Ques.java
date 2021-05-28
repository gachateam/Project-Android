package com.gacha.quizapp.Model;

public abstract class Ques {
    protected int type;
    protected int id;
    protected int category;

    public static int TYPE_MUL_QUES = 1;
    public static int TYPE_READ = 2;
    public static int TYPE_SPEAK = 3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
