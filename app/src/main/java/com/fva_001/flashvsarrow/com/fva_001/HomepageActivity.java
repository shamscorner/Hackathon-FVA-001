package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by ShamimH on 17-Mar-16.
 */
public class HomepageActivity extends AppCompatActivity {

    private MediaPlayer background_music;
    private boolean music_playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

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
                Intent intent = new Intent(HomepageActivity.this, MapFound.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // set the background music for the homepage
        background_music = MediaPlayer.create(HomepageActivity.this, R.raw.background_homepage_music);
        background_music.setLooping(true);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //play the background music
        if(music_playing == false){
            background_music.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        background_music.stop();
    }
    @Override
    protected void onStop() {
        super.onStop();
        background_music.stop();
        background_music.release();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        background_music.stop();
        background_music.release();
        android.os.Process.killProcess(Process.myPid());
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
