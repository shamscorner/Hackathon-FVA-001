package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ShamsCorner on 29-Mar-16.
 */
public class About extends AppCompatActivity {
    private MediaPlayer background_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        //make a transition
        overridePendingTransition(R.anim.slide_up, R.anim.fade_out);

        //set the background music
        background_music = MediaPlayer.create(About.this, R.raw.homepage_music);
        background_music.start();
        background_music.setLooping(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // set the background music for the homepage
        if(background_music != null){
            background_music.setLooping(true);
            background_music.start();
        }else{
            background_music = MediaPlayer.create(About.this, R.raw.homepage_music);
            background_music.start();
            background_music.setLooping(true);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        //play the background music
        if(background_music != null){
            background_music.start();
        }else{
            background_music = MediaPlayer.create(About.this, R.raw.homepage_music);
            background_music.start();
            background_music.setLooping(true);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(background_music != null){
            background_music.stop();
        }
    }
    @Override
    public void onDestroy() {
        if(background_music != null) {
            background_music.release();
            background_music = null;
        }
        super.onDestroy();
    }
    //handle the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.slide_down);
    }
}
