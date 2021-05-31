package com.gacha.quizapp.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesListen;
import com.gacha.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class QuesListenFragment extends AbstractFragment {
    private QuesListen quesListen;
    private EditText editText;
    TextToSpeech textToSpeech;

    @Override
    public void setQuestion(Ques question) {
        this.quesListen = (QuesListen) question;
    }

    @Override
    public Ques updateUserInteraction(ArrayList<Ques> listQues, int questionID) {
        QuesListen ques = (QuesListen) listQues.get(questionID);

        ques.setUserAns(editText.getText().toString());

        return ques;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_listen_fragment, container, false);

        editText = view.findViewById(R.id.resultListen);
        textToSpeech = new TextToSpeech(getContext(), status -> {
            Log.d("TAG", "onCreateView: create successfully " + status);
        });

        textToSpeech.setLanguage(Locale.ENGLISH);

        view.findViewById(R.id.btnVoice).setOnClickListener(v -> speakOut());

        return view;
    }

    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    private void speakOut() {
        // Text to Speak
        String toSpeak = quesListen.getQues();
//        Toast.makeText(getContext(), toSpeak, Toast.LENGTH_SHORT).show();
        // A random String (Unique ID).
        String utteranceId = UUID.randomUUID().toString();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}
