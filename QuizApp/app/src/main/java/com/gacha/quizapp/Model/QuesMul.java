package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class QuesMul extends Ques implements Serializable {
    private int ansC;
    private String ques;
    private ArrayList<String> ans;

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
        super(type,id,category);
        this.ansC = ansC;
        this.ques = ques;
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

    @Override
    public int getPoint() {
        return 0;
    }
}
