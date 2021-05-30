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
        super(type,id,category);
        this.ques = ques;
    }

    public QuesListen() {
        super();
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
