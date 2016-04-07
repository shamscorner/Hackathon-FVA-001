package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ShamimH on 23-Mar-16.
 */
public class SeekStoreroom extends AppCompatActivity {

    private TextView textIntro;
    private int phase = 0;
    private String[] text = new String[7];

    Button btn_skip, btn_next;

    LinearLayout storeHolder, btn_go_store_layout;

    Animation animFadein, animSlideup, animFadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.seek_store_room);

        //load fadein animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animSlideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        // Play the animation on the intro man
        ImageView mImageIntroMan = (ImageView)findViewById(R.id.image_intro_man);
        ((AnimationDrawable)mImageIntroMan.getBackground()).start();

        //get the text intro
        textIntro = (TextView)findViewById(R.id.text_introman);
        textIntro.startAnimation(animFadein);

        //set the intro nam text
        text[0] = "Hello ! I think you have already known a lot of stuff on our worst side of the environment. And I think you do not forget the best part of out environment too.";
        text[1] = "But who actually responsible for that? Why our beautiful cities are turning into something like hell? What do you think about that ?";
        text[2] = "Of course, we are. And you are also a part of it. You are also responsible for all kind of things that are related to this worst side of our beautiful environment.";
        text[3] = "So it's time for you to think something better, to make something better  to save our world and also the existence of mankind. ";
        text[4] = "Don't worry. You are not on your own. I will always be on your side.";
        text[5] = "If you are ready, then let's get started.";
        text[6] = "You have to find a map to explore so go to your store room";

        //set the default text
        textIntro.setText(text[0]);

        //initialize the all button from the seek_store room layout
        btn_skip = (Button)findViewById(R.id.btn_skip);
        btn_next = (Button)findViewById(R.id.btn_next);

        storeHolder = (LinearLayout)findViewById(R.id.go_to_store_holder);
        storeHolder.setVisibility(View.INVISIBLE);

        // go to store room
        btn_go_store_layout = (LinearLayout)findViewById(R.id.go_to_store_holder);
        btn_go_store_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                Intent intent = new Intent(getApplicationContext(), MapFound.class);
                startActivity(intent);
            }
        });
    }

    public void textNext(View v){
        phase++;
        if(phase > 6){
            v.setVisibility(View.INVISIBLE);
            btn_skip.setVisibility(View.INVISIBLE);
            textIntro.setVisibility(View.INVISIBLE);
            textIntro.startAnimation(animFadeout);
            storeHolder.setVisibility(View.VISIBLE);
            storeHolder.startAnimation(animSlideup);
        }else{
            textIntro.setText(text[phase]);
            textIntro.startAnimation(animFadein);
        }
    }

    public void textSkip(View v){
        textIntro.setText(text[6]);
        textIntro.startAnimation(animFadein);
        v.setEnabled(false);
        phase = 6;
    }

    // handle the go back function
    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
        startActivity(intent);
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }
}
