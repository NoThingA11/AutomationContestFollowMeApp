package com.sitthinon.jinpaoautomationcontest;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medialablk.easygifview.EasyGifView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    public EasyGifView gifEye,gifMouth;
    public DatabaseReference FollowMeDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Config Database
        FollowMeDatabase = FirebaseDatabase.getInstance().getReference();
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);

//        Define Text to speak
        tts = new TextToSpeech(this, this,"com.google.android.tts");
        tts.setLanguage(Locale.ENGLISH);
        tts.speak("Welcome to jinpao automation contest two thousand nineteen", TextToSpeech.QUEUE_ADD, null);


//        Run Normal Gif Face
        runNormalGifFace();

        FollowMeDatabase.child("SpeakCommand").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tts.setLanguage(Locale.ENGLISH);
                tts.speak("Welcome to jinpao automation contest two thousand nineteen", TextToSpeech.QUEUE_ADD, null);
                Log.d("SpeakCommand","Follow Me Speak Now");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (tts.isSpeaking()) {

        }
    }

    public void runNormalGifFace() {
        gifEye = findViewById(R.id.gifEye);
        gifMouth = findViewById(R.id.gifMouth);
        gifEye.setGifFromResource(R.drawable.eyeblink);
        gifMouth.setGifFromResource(R.drawable.mouth3);
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
