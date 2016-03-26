package com.fva_001.flashvsarrow.com.fva_001;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by ShamimH on 21-Mar-16.
 */
public class MapFound extends AppCompatActivity {

    private MediaPlayer map_found_music;
    private MediaPlayer background_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_map);

        // Play the animation on the intro man
        ImageView mImageIntroMan = (ImageView)findViewById(R.id.image_intro_man_find_map);
        ((AnimationDrawable)mImageIntroMan.getBackground()).start();

        //initialize the sound effect
        map_found_music = MediaPlayer.create(getApplicationContext(), R.raw.map_found);

        background_music = MediaPlayer.create(MapFound.this, R.raw.background_jungle);
        background_music.start();
        background_music.setLooping(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //play the background music
        if(background_music != null){
            background_music.start();
            background_music.setLooping(true);
        }else{
            background_music = MediaPlayer.create(MapFound.this, R.raw.background_jungle);
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

    // this is called when the gamer will find the map
    public void openMap(View view){
        map_found_music.start();
        MapFoundDialog dialog = new MapFoundDialog(MapFound.this);
        dialog.show();
    }

    // this function is called when gamer want to go back to the homepage
    public void goHomapageScreen(View view){
        goBack();
        new ButtonClick(getApplicationContext(), view);
    }

    // this is for the language in the dashboard
    public void openLanguageFindMap(View v){
        new ButtonClick(getApplicationContext(), v);
        CustomDialogFragment dialog = new CustomDialogFragment(MapFound.this);
        dialog.show();
    }

    // this is for the story in the dashboard
    public void openStory(View v){
        new ButtonClick(getApplicationContext(), v);

    }

    //this is for the progress dialog
    public void openProgressFindMap(View v){
        new ButtonClick(getApplicationContext(), v);
    }

    // handle the go back function
    private void goBack(){
        GoHomepage dialog = new GoHomepage(MapFound.this);
        dialog.show();
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }

}
