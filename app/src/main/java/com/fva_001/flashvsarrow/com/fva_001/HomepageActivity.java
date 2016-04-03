package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;

/**
 * Created by ShamimH on 17-Mar-16.
 */
public class HomepageActivity extends AppCompatActivity {

    private MediaPlayer background_music;
    private boolean music_playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage);

        background_music = MediaPlayer.create(HomepageActivity.this, R.raw.homepage_music);
        background_music.start();
        background_music.setLooping(true);

        //get the sound button and handle the listener
        final ToggleButton btn_sound_toggle = (ToggleButton)findViewById(R.id.btn_sound);
        btn_sound_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    background_music.pause();
                    music_playing = true;
                } else {
                    // The toggle is disabled
                    background_music.start();
                    music_playing = false;
                }
                new ButtonClick(getApplicationContext(), buttonView);
            }
        });

        // handle the listener for the exit button
        Button btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                goBack();
            }
        });

        //handle the language button
        Button btn_language = (Button)findViewById(R.id.btn_language);
        btn_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                CustomDialogFragment Dialog_language = new CustomDialogFragment(HomepageActivity.this);
                Dialog_language.show();
            }
        });

        //handle the start button
        Button btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                Intent intent = new Intent(getApplicationContext(), SeekStoreroom.class);
                //intent.putExtra("type", 1);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //handle the progress button
        ImageButton btn_progress = (ImageButton)findViewById(R.id.btn_progress);
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                intent.putExtra("type", 2);
                intent.putExtra("exit", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.fade_out);
            }
        });

        //handle for the about button
        Button btnAbout = (Button)findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // set the background music for the homepage
        if(background_music != null){
            background_music.setLooping(true);
            background_music.start();
        }else{
            background_music = MediaPlayer.create(HomepageActivity.this, R.raw.homepage_music);
            background_music.start();
            background_music.setLooping(true);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        //play the background music
        if(background_music != null){
            if(music_playing == false){
                background_music.start();
            }else{
                background_music = MediaPlayer.create(HomepageActivity.this, R.raw.homepage_music);
                background_music.start();
                background_music.setLooping(true);
            }
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


    //the function for the back button
    private void goBack(){
        Dialog_exit dialog_exit = new Dialog_exit(HomepageActivity.this);
        dialog_exit.show();
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }
}
