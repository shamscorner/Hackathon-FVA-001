package com.fva_001.flashvsarrow.com.fva_001;

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

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*
        //exit the app if the exit button is pressed
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        */

        // Play the animation on the splash screen
        ImageView mImageAnimatedSplash = (ImageView)findViewById(R.id.animated_splash);
        ((AnimationDrawable)mImageAnimatedSplash.getBackground()).start();

        // Now play the splash screen first
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(10000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openSplash = new Intent(getApplicationContext(), HomepageActivity.class);
                    startActivity(openSplash);
                }
            }
        };
        timer.start();
    }

    @Override
    public void onDestroy() {
        //finish();
        //android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
