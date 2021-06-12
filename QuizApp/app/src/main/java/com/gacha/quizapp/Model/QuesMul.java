package com.gacha.quizapp.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class QuesMul extends Ques implements Serializable {
    private int ansC;
    private String ques;
    private ArrayList<String> ans;
    private int userAns = -1;

    private static final String TAG = QuesMul.class.getSimpleName();

    public int getUserAns() {
        return userAns;
    }

    public void setUserAns(int userAns) {
        this.userAns = userAns;
    }

    public int getAnsC() {
        return ansC;
    }

    public void setAnsC(int ansC) {
        this.ansC = ansC;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public ArrayList<String> getAns() {
        return ans;
    }

    public void setAns(ArrayList<String> ans) {
        this.ans = ans;
    }

    public QuesMul() {
        ansC = -1;
    }

    public QuesMul(int id,int ansC, int category, String ques, int type, ArrayList<String> ans) {
        super(type,id,category);
        this.ansC = ansC;
        this.ques = ques;
        this.ans = ans;
    }

    @NonNull
    @Override
    public String toString() {
        return "QuesMul{" +
                "ansC=" + ansC +
                ", ques='" + ques + '\'' +
                ", ans=" + ans +
                ", userAns=" + userAns +
                ", type=" + type +
                ", id=" + id +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean getPoint() {
        Log.d("TAG", "getPoint: "+(ansC == userAns));
        return ansC == userAns;
    }
}
