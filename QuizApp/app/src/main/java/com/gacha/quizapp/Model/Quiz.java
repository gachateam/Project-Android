package com.gacha.quizapp.Model;

import java.util.ArrayList;

public class Quiz {
    private String ques;
    private ArrayList<String> ans;
    private int ansTrue;

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

    public int getAnsTrue() {
        return ansTrue;
    }

    public void setAnsTrue(int ansTrue) {
        this.ansTrue = ansTrue;
    }

    public Quiz(String ques, ArrayList<String> ans, int ansTrue) {
        this.ques = ques;
        this.ans = ans;
        this.ansTrue = ansTrue;
    }
}
