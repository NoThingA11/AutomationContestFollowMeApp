package com.sitthinon.jinpaoautomationcontest;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    public EasyGifView gifEye,gifMouth,gifMouthMove;
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



//        Set up Gif
        gifEye = findViewById(R.id.gifEye);
        gifMouth = findViewById(R.id.gifMouth);
        gifMouthMove = findViewById(R.id.gifMouthMove);
        gifEye.setGifFromResource(R.drawable.eyeblink);
        gifMouth.setGifFromResource(R.drawable.mouth3);
        gifMouthMove.setGifFromResource(R.drawable.audiowave2);

        gifEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });

//        Run Normal Gif Face
        runNormalGifFace();

//        Define Text to speak
        tts = new TextToSpeech(this, this,"com.google.android.tts");
        tts.setLanguage(Locale.ENGLISH);
        tts.speak("Welcome to jinpao automation contest two thousand nineteen", TextToSpeech.QUEUE_ADD, null);

        FollowMeDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("SpeakCommand","In Method onDataChange");
                Map map = (Map)dataSnapshot.getValue();
                String speakCommand = String.valueOf(map.get("SpeakCommand"));
                if (speakCommand.equals("1")) {
                    runMoveGifFace();
                    tts.setLanguage(Locale.ENGLISH);
                    tts.setSpeechRate((float) 0.9);
                    tts.speak("Good morning everyone", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("I am the robot, My name is Follow me", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("My job is to be a guide to introduce product information in our product show room", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("When you visit out company , Please let me be a guide for you.", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("Today , welcome to join jinpao automation contest two thousand nineteen", TextToSpeech.QUEUE_ADD, null);
                } else if (speakCommand.equals("2")) {
                    runNormalGifFace();
                } else if (speakCommand.equals("3")) {
                    runMoveGifFace();
                    tts.setLanguage(new Locale("th"));
                    tts.setSpeechRate((float) 1);
                    tts.speak("สวัสดีตอนเช้าค่ะ", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("ฉันคือหุ่นยนต์ ฉันชื่อ follow me", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("มีหน้าที่เป็นไกด์พาชมผลิตภัณฑ์ของบริษัท jinpao precision industrial", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("วันหลังมาชมบริษัทเรา ให้ฉันจะพาชมและแนะนำผลิตภัณฑ์ของบริษัทนะคะ", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("วันนี้ยินดีต้อนรับทุกท่านมาร่วมงานของ jinpao Automation Contest สองพันสิบเก้า", TextToSpeech.QUEUE_ADD, null);
                    tts.speak("ขออนุญาตส่งไมโครโฟนให้พิธีกรดำเนินการต่อนะคะ ขอบคุณค่ะ", TextToSpeech.QUEUE_ADD, null);
                } else {
                    runNormalGifFace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });






    }


    public void runNormalGifFace() {
        Log.d("SpeakCommand","In Method Normal");
        gifMouth.setVisibility(View.VISIBLE);
        gifMouthMove.setVisibility(View.GONE);
    }

    public void runMoveGifFace() {
        Log.d("SpeakCommand","In Method Move");
        gifMouth.setVisibility(View.GONE);
        gifMouthMove.setVisibility(View.VISIBLE);
    }



    @Override
    public void onInit(int status) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


}
