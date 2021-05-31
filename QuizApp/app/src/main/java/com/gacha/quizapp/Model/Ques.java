package com.gacha.quizapp.Model;

import java.io.Serializable;

public abstract class Ques implements Serializable {
    protected int type;
    protected int id;
    protected int category;

    public static int TYPE_MUL_QUES = 1;
    public static int TYPE_LISTEN = 2;
    public static int TYPE_SPEAK = 3;
    public static int TYPE_MUL_QUES_IMAGE = 4;

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

    public abstract boolean getPoint();

    public Ques(int type, int id, int category) {
        this.type = type;
        this.id = id;
        this.category = category;
    }

    public Ques() {
    }
}
