package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play the animation on the splash screen
        ImageView mImageAnimatedSplash = (ImageView)findViewById(R.id.animated_splash);
        ((AnimationDrawable)mImageAnimatedSplash.getBackground()).start();

        // Now play the splash screen first
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(10000);
                }catch (InterruptedException e){
                    //e.printStackTrace();
                    Toast error_toast = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
                    error_toast.show();
                }finally {
                    Intent openSplash = new Intent(getApplicationContext(), HomepageActivity.class);
                    startActivity(openSplash);
                }
            }
        };
        timer.start();
    }
}
