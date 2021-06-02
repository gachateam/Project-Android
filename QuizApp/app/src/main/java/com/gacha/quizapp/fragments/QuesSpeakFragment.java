package com.gacha.quizapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesSpeak;
import com.gacha.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class QuesSpeakFragment extends AbstractFragment  {
    private QuesSpeak quesSpeak;
    private String result;

    @Override
    public void setQuestion(Ques question) {
        this.quesSpeak = (QuesSpeak) question;
    }

    @Override
    public Ques updateUserInteraction(ArrayList<Ques> listQues, int questionID) {
        QuesSpeak quesSpeak = (QuesSpeak) listQues.get(questionID);

        quesSpeak.setUserAns(result);

        return quesSpeak;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_speak_fragment,container,false);

        TextView textView = view.findViewById(R.id.ques);

        textView.setText(this.quesSpeak.getQues());

        view.findViewById(R.id.btnVoice).setOnClickListener(this::getSpeechInput);

        return view;
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en-US");

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(getContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                this.result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
            }
        }
    }
}
