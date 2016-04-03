package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by ShamimH on 25-Mar-16.
 */
public class CreateAccount extends AppCompatActivity {

    private Button btnPlay, btnProgress;
    private ImageButton btnExit;
    private EditText inputName, inputPassword;
    private int type, exit;
    private MediaPlayer background_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        //set the background music
        background_music = MediaPlayer.create(CreateAccount.this, R.raw.homepage_music);
        background_music.start();
        background_music.setLooping(true);

        btnPlay = (Button)findViewById(R.id.btn_play);
        btnPlay.setVisibility(View.INVISIBLE);
        btnProgress = (Button)findViewById(R.id.btn_see_progress);
        btnProgress.setVisibility(View.INVISIBLE);
        btnExit = (ImageButton)findViewById(R.id.btn_exit_progress);

        inputName = (EditText)findViewById(R.id.input_name);
        inputPassword = (EditText)findViewById(R.id.input_password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type");
            exit = extras.getInt("exit");
        }

        if(type == 1){
            btnPlay.setVisibility(View.VISIBLE);
        }else if(type == 2){
            btnProgress.setVisibility(View.VISIBLE);
        }

        if(exit == 0){
            btnExit.setVisibility(View.INVISIBLE);
        }else if(exit == 1){
            btnExit.setVisibility(View.VISIBLE);
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                if(inputName.getText().toString().trim().matches("") && inputPassword.getText().toString().trim().matches("")){
                    Toast.makeText(getApplicationContext(), "You haven't fill this form", Toast.LENGTH_LONG).show();
                }else{
                    //open something when account is created
                }
            }
        });

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                if(inputName.getText().toString().trim().matches("") && inputPassword.getText().toString().trim().matches("")){
                    Toast.makeText(getApplicationContext(), "You haven't fill this form", Toast.LENGTH_LONG).show();
                }else{
                    //open up the progress intent
                }
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
            background_music = MediaPlayer.create(CreateAccount.this, R.raw.homepage_music);
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
            background_music = MediaPlayer.create(CreateAccount.this, R.raw.homepage_music);
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

    //the function for the back button
    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_in_right);
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        if(exit == 0){
            Intent intent = new Intent(getApplicationContext(), MapFound.class);
            startActivity(intent);
        }else if(exit == 1){
            goBack();
        }
    }

    public void backtohomepage(View v){
        new ButtonClick(getApplicationContext(), v);
        goBack();
    }
}
