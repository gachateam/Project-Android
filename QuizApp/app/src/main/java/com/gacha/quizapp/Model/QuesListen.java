package com.gacha.quizapp.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class QuesListen extends Ques implements Serializable {
    private String ques;

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public QuesListen(String ques, int id, int category, int type) {
        this.ques = ques;
        this.id = id;
        this.category = category;
        this.type = type;
    }

    public QuesListen() {
    }

    @NonNull
    @Override
    public String toString() {
        return "QuesRead{" +
                "ques='" + ques + '\'' +
                ", id=" + id +
                ", category=" + category +
                ", type=" + type +
                '}';
    }

    @Override
    public int getPoint() {
        return 0;
    }
}
