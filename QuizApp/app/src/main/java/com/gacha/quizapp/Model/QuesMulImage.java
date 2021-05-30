package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class QuesMulImage extends Ques implements Serializable {
    static class AnsImage
    {
        public String image;
        public String ans;
    };
    private int ansC;
    private String ques;
    private ArrayList<AnsImage> ans;

    public int getAnsC() {
        return ansC;
    }

    public void setAnsC(int ansC) {
        this.ansC = ansC;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public ArrayList<AnsImage> getAns() {
        return ans;
    }

    public void setAns(ArrayList<AnsImage> ans) {
        this.ans = ans;
    }

    public QuesMulImage() {
    }

    public QuesMulImage(int id,int ansC, int category, String ques, int type, ArrayList<AnsImage> ans) {
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

    @Override
    public int getPoint() {
        return 0;
    }
}
