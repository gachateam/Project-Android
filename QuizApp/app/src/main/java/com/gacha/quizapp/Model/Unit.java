package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit implements Serializable {
    private String unitName;
    private ArrayList<Quiz> quizList;
    private String unitDescription;

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public Unit(String unitName, ArrayList<Quiz> quizList, String unitDescription) {
        this.unitName = unitName;
        this.quizList = quizList;
        this.unitDescription = unitDescription;
    }

    public Unit() {
    }
}
