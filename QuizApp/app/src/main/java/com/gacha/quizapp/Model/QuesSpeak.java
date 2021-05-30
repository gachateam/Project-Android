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
        super(type,id,category);
        this.ques = ques;
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
