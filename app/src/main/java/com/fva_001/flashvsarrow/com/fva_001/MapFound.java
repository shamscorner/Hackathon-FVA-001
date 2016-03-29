package com.fva_001.flashvsarrow.com.fva_001;

import android.app.ActionBar;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ShamimH on 21-Mar-16.
 */
public class MapFound extends AppCompatActivity {

    private MediaPlayer map_found_music;
    private MediaPlayer background_music;
    private ImageButton[] btn;
    private TextView popup;
    private int count = 0;
    private String[] text;
    private AlphaAnimation alphaAnimin, alphaAnimout;
    private ScaleAnimation grow, shrink;
    private AnimationSet growAndShrink;

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

        //initialize all buttons
        btn = new ImageButton[4];
        btn[0] = (ImageButton)findViewById(R.id.btn_map_found_in_wall);
        btn[1] = (ImageButton)findViewById(R.id.btn_story_find_map);
        btn[2] = (ImageButton)findViewById(R.id.btn_language_find_map);
        btn[3] = (ImageButton)findViewById(R.id.btn_progress_find_map);

        //initialize the textview
        popup = (TextView)findViewById(R.id.popup_msg);
        text = new String[11];

        text[0] = "Hello There !";
        text[1] = "Let's do it.";
        text[2] = "I know you can make it.";
        text[3] = "Why are you waiting for ?";
        text[4] = "You can't late this happen.";
        text[5] = "You are better than this.";
        text[6] = "Ya..., I am getting bored.";
        text[7] = "Let's change the world.";
        text[8] = "Don't be afraid of.";
        text[9] = "I am always with you.";


        final float growTo = 1.2f;
        final long duration = 1200;

        grow = new ScaleAnimation(1, growTo, 1, growTo, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(duration / 2);
        shrink = new ScaleAnimation(growTo, 1, growTo, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(duration / 2);
        shrink.setStartOffset(4000);
        grow.setStartOffset(9000);
        growAndShrink = new AnimationSet(true);
        growAndShrink.setInterpolator(new LinearInterpolator());
        growAndShrink.addAnimation(grow);
        growAndShrink.addAnimation(shrink);

        growAndShrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int n = 0 + (int) (Math.random() * 4);
                btn[n].startAnimation(growAndShrink);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        int n = 0 + (int) (Math.random() * 4);
        btn[n].startAnimation(growAndShrink);



        // fade out view nicely after 3 seconds
        alphaAnimin = new AlphaAnimation(0.0f,1.0f); // animation in
        alphaAnimout = new AlphaAnimation(1.0f,0.0f); // animation out
        alphaAnimin.setStartOffset(5000);
        alphaAnimout.setStartOffset(3000);
        alphaAnimin.setDuration(400);
        alphaAnimout.setDuration(500);

        alphaAnimin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                popup.startAnimation(alphaAnimout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        alphaAnimout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                count++;
                if (count == 0) {
                    popup.setText(text[0]);
                } else if (count == 1) {
                    popup.setText(text[1]);
                } else {
                    if (count > 9) {
                        count = 0;
                    }
                    int n = 0 + (int) (Math.random() * 10);
                    popup.setText(text[n]);
                }
                popup.startAnimation(alphaAnimin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        popup.setText(text[0]);
        popup.setAnimation(alphaAnimin);
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
        DialogProgress dialogProgress = new DialogProgress(MapFound.this);
        dialogProgress.show();
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
