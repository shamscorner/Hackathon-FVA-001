package com.fva_001.flashvsarrow.com.fva_001;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShamimH on 24-Mar-16.
 */
public class LevelCompleted extends AppCompatActivity {

    private LinearLayout headerHolder;
    private Animation animFadein, animSlideup;
    private LinearLayout objAnimFinal;
    private TextView objDivider;
    private Map<Integer, LinearLayout> objAnim;
    long time = 5000;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_completed);

        //initialize the animations
        animSlideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadein.setDuration(time);
        animSlideup.setDuration(1000);

        // initialize the views
        headerHolder = (LinearLayout)findViewById(R.id.level_completed_header_holder);
        headerHolder.startAnimation(animSlideup);

        objAnim = new HashMap<Integer, LinearLayout> ();
        objAnim.put(1, (LinearLayout) findViewById(R.id.completed_task_1));
        objAnim.put(2, (LinearLayout) findViewById(R.id.completed_task_2));
        objAnim.put(3, (LinearLayout) findViewById(R.id.completed_task_3));
        objAnim.put(4, (LinearLayout) findViewById(R.id.completed_task_4));
        objAnim.put(5, (LinearLayout) findViewById(R.id.completed_task_5));
        objAnim.put(6, (LinearLayout) findViewById(R.id.completed_task_6));
        objAnim.put(7, (LinearLayout) findViewById(R.id.completed_task_7));


        objAnimFinal = (LinearLayout)findViewById(R.id.completed_task_final);
        objDivider = (TextView)findViewById(R.id.completed_task_divider);


        int delay;
        for (i = 1; i < 8; i++) {
            delay=(i*90)+1500;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                objAnim.get(i).setScaleY(0f);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                objAnim.get(i).setScaleX(0f);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    objAnim.get(i).animate()
                        .setInterpolator(new DecelerateInterpolator(1.6f))
                        .setStartDelay(delay)
                        .setDuration(400)
                        .scaleY(1f)
                        .scaleX(1f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {

                            }
                        })
                        .start();
                }
            }
        }
        objDivider.startAnimation(animFadein);
        objAnimFinal.startAnimation(animFadein);

    }
    public void goHomeCompleted(View v){
        new ButtonClick(getApplicationContext(), v);
        GoHomepage go = new GoHomepage(LevelCompleted.this);
        go.show();
    }

    public void goMapCompleted(View v){
        new ButtonClick(getApplicationContext(), v);
        Intent intent = new Intent(getApplicationContext(), EntireMap.class);
        startActivity(intent);
    }

    // handle the go back function
    private void goBack(){
        GoHomepage dialog = new GoHomepage(LevelCompleted.this);
        dialog.show();
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }
}
