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

    Button btn_skip, btn_next, btn_go_store;

    LinearLayout storeHolder;

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
        text[0] = "Hello, Shamim ! I think you have already known a lot of stuff on our bad side of the environment. And I think you do not forget the good side of the beautiful environment in our world.";
        text[1] = "But who actually responsible for that? Why our beautiful cities are  turning into something like hell. What do you think about that Shamim?";
        text[2] = "Of course, we are. And you are also a part of it. You are also responsible for all kind of things that are related to this bad side of our environment.";
        text[3] = "So it's time for you to think something better, to make something better  to save our world and also the existence of mankind. ";
        text[4] = "Don't worry. You are not on your own. I will always be on your side.";
        text[5] = "If you are ready, then let's get started.";

        //set the default text
        textIntro.setText(text[0]);

        //initialize the all button from the seek_store room layout
        btn_skip = (Button)findViewById(R.id.btn_skip);
        btn_next = (Button)findViewById(R.id.btn_next);

        storeHolder = (LinearLayout)findViewById(R.id.go_to_store_holder);
        storeHolder.setVisibility(View.INVISIBLE);

        //go back to the homepage
        Button btnGoback = (Button)findViewById(R.id.btn_gohomepage);
        btnGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        // go to store room
        btn_go_store = (Button)findViewById(R.id.btn_go_to_store_room);
        btn_go_store.setOnClickListener(new View.OnClickListener() {
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
        if(phase > 5){
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
        textIntro.setText(text[5]);
        textIntro.startAnimation(animFadein);
        v.setEnabled(false);
        phase = 5;
    }

    // handle the go back function
    private void goBack(){
        GoHomepage dialog = new GoHomepage(SeekStoreroom.this);
        dialog.show();
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }
}
