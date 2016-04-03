package com.fva_001.flashvsarrow.com.fva_001;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Play the animation on the splash screen
        ImageView mImageAnimatedSplash = (ImageView)findViewById(R.id.animated_splash);
        ((AnimationDrawable)mImageAnimatedSplash.getBackground()).start();

        // Now play the splash screen first
        Thread timer = new Thread(){
            public void run(){
                try{
                    int timer=0;
                    while(timer<7100) {
                        sleep(100);
                        timer = timer + 100;
                    }
                    Intent openSplash = new Intent(MainActivity.this, HomepageActivity.class);
                    startActivity(openSplash);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }
}
