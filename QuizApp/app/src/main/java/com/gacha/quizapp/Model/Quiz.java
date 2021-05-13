package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String cauhoi;
    private ArrayList<String> dapan;
    private String dapandung;

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public ArrayList<String> getDapan() {
        return dapan;
    }

    public void setDapan(ArrayList<String> dapan) {
        this.dapan = dapan;
    }

    public String getDapandung() {
        return dapandung;
    }

    public void setDapandung(String dapandung) {
        this.dapandung = dapandung;
    }

    public Quiz(String ques, ArrayList<String> ans, String ansTrue) {
        this.cauhoi = ques;
        this.dapan = ans;
        this.dapandung = ansTrue;
    }

    public Quiz() {
    }
}
