package com.gacha.quizapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit implements Serializable {
    private String name;
    private ArrayList<Integer> ques;
    private String description;

    private static final String TAG = QuesSpeak.class.getSimpleName();

    public ArrayList<Integer> getQues() {
        return ques;
    }

    public void setQues(ArrayList<Integer> ques) {
        this.ques = ques;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit(String unitName, ArrayList<Integer> quizList, String unitDescription) {
        this.name = unitName;
        this.ques = quizList;
        this.description = unitDescription;
    }

    public Unit() {
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", ques=" + ques +
                ", description='" + description + '\'' +
                '}';
    }
}
