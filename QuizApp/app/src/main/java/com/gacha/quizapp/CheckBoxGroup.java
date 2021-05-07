package com.gacha.quizapp;

import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Collections;

public class CheckBoxGroup {
    private final ArrayList<CheckBox> checkBoxes;
    public CheckBoxGroup(CheckBox ...cbs) {
        checkBoxes = new ArrayList<>();
        Collections.addAll(checkBoxes, cbs);
        for (CheckBox cb : checkBoxes) {
            View.OnClickListener onClickListener = v -> {
                for (int i = 0; i < checkBoxes.size(); i++) {
                    checkBoxes.get(i).setChecked(false);
                }
                checkBoxes.get(checkBoxes.indexOf((CheckBox) v)).setChecked(true);
            };
            cb.setOnClickListener(onClickListener);
        }
    }
}
