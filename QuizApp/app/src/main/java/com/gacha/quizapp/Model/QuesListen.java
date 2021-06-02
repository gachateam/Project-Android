package com.gacha.quizapp.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class QuesListen extends Ques implements Serializable {
    private String ques;
    private String userAns;

    public String getUserAns() {
        return userAns;
    }

    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }

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
        return "QuesListen{" +
                "ques='" + ques + '\'' +
                ", userAns='" + userAns + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean getPoint() {
        Log.d("TAG", "getPoint: "+((userAns != null && userAns.equals(ques))?true:false));
        return (userAns != null && userAns.equals(ques))?true:false;
    }
}
