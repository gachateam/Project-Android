package com.gacha.quizapp.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class QuesSpeak extends Ques implements Serializable {
    private String ques;

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public QuesSpeak(String ques, int id, int category,int type) {
        this.ques = ques;
        this.id = id;
        this.category = category;
        this.type = type;
    }

    public QuesSpeak() {
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
