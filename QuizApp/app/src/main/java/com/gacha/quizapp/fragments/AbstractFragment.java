package com.gacha.quizapp.fragments;

import androidx.fragment.app.Fragment;

import com.gacha.quizapp.Model.Ques;

import java.util.ArrayList;

public abstract class AbstractFragment extends Fragment {
    public abstract void setQuestion(Ques question);
    public abstract Ques updateUserInteraction(ArrayList<Ques> listQues,int questionID);
}
