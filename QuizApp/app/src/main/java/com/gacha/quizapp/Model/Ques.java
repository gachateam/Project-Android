package com.gacha.quizapp.Model;

import androidx.fragment.app.Fragment;

public abstract class Ques {
    protected int type;
    protected int id;
    protected int category;

    public static int TYPE_MUL_QUES = 1;
    public static int TYPE_READ = 2;
    public static int TYPE_SPEAK = 3;
    public static int TYPE_MUL_QUES_IMAGE = 4;

    public int getQuesId() {
        return id;
    }

    public void setQuesId(int id) {
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

    public abstract int getPoint();
}
