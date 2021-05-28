package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class QuesMul extends Ques implements Serializable {
    private int ansC;
    private String ques;
    private ArrayList<String> ans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    }

    public QuesMul(int id,int ansC, int category, String ques, int type, ArrayList<String> ans) {
        this.id = id;
        this.ansC = ansC;
        this.category = category;
        this.ques = ques;
        this.type = type;
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "QuesMul{" +
                "\nid=" + id +
                "\n, ansC='" + ansC + '\'' +
                "\n, category=" + category +
                "\n, ques='" + ques + '\'' +
                "\n, type=" + type +
                "\n, ans=" + ans +
                '}';
    }
}
