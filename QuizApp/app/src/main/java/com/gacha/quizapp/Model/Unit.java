package com.gacha.quizapp.Model;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit implements Serializable {
    private String name;
    private ArrayList<Integer> ques;
    private String description;
    private int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

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

    public Unit(String unitName, ArrayList<Integer> quizList, String unitDescription,int category) {
        this.name = unitName;
        this.ques = quizList;
        this.description = unitDescription;
        this.category = category;
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

    public static ArrayList<Unit> getAllQuesByCategoryInFirebase(DataSnapshot snapshot,int catId){
        ArrayList<Unit> units = new ArrayList<>();

        for (DataSnapshot data : snapshot.getChildren()) {
            if (Integer.parseInt(data.child("category").toString())==catId){
                units.add(data.getValue(Unit.class));
            }
        }

        return units;
    }
    public static ArrayList<Unit> getAllQuesByCategoryInList(ArrayList<Unit> units,int catId){
        ArrayList<Unit> units1 = new ArrayList<>();

        for (Unit unit : units) {
            if (unit.getCategory()==catId){
                units1.add(unit);
            }
        }

        return units1;
    }

    public ArrayList<Ques> getQuesList(ArrayList<Ques> listQues){
        ArrayList<Ques> quesMul = new ArrayList<>();

        for (Integer quesId : ques) {
            for (Ques quesMu : listQues) {
                if (quesMu.getId() == quesId){
                    quesMul.add(quesMu);
                    break;
                }
            }
        }

        return quesMul;
    }
}
