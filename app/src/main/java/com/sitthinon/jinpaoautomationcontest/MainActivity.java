package com.sitthinon.jinpaoautomationcontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.medialablk.easygifview.EasyGifView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        Run Normal Gif Face
        runNormalGifFace();
    }

    private void runNormalGifFace() {
        EasyGifView gifEye = findViewById(R.id.gifEye);
        EasyGifView gifMouth = findViewById(R.id.gifMouth);
        gifEye.setGifFromResource(R.drawable.eye);
    }


}
