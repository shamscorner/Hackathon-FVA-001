package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by ShamimH on 22-Mar-16.
 */
public class EntireMap extends AppCompatActivity implements View.OnTouchListener {

    private ImageView map_area;
    private MediaPlayer background_music;
    int pollution, unorganized, risk;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.entire_map);

        //initialize the sharedpreferences file
        pref = getApplicationContext().getSharedPreferences("core_fva", MODE_PRIVATE);
        pollution = pref.getInt("PERCENT_POL", 0);
        unorganized = pref.getInt("PERCENT_UN", 0);
        risk = pref.getInt("PERCENT_RISK", 0);

        map_area = (ImageView)findViewById(R.id.map_area);
        map_area.setOnTouchListener(this);

        // Play the animation on the intro man
        ImageView mImageIntroMan = (ImageView)findViewById(R.id.image_intro_man_entire_map);
        ((AnimationDrawable)mImageIntroMan.getBackground()).start();

        background_music = MediaPlayer.create(EntireMap.this, R.raw.background_jungle);
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
            background_music = MediaPlayer.create(EntireMap.this, R.raw.background_jungle);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getAction();
        final int evX = (int) event.getX();
        final int evY = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN :

                break;
            case MotionEvent.ACTION_UP :
                int touchColor = getHotspotColor (R.id.map_area, evX, evY);
                int tolerance = 25;
                if (closeMatch(Color.rgb(51, 153, 153), touchColor, tolerance)) {
                    // this is the area for the rangpur
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "rangpur", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(204, 204, 51), touchColor, tolerance)) {
                    // this is the area for the rajshahi
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "rajshahi", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(0, 115, 85), touchColor, tolerance)) {
                    // this is the area for the dhaka
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "dhaka", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(255, 51, 102), touchColor, tolerance)) {
                    // this is the area for the khulna
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "khulna", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(153, 187, 102), touchColor, tolerance)) {
                    // this is the area for the barisal
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "barishal", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(204, 51, 51), touchColor, tolerance)) {
                    // this is the area for the chittagong
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "chittagong", pollution, unorganized, risk);
                    dialogArea.show();
                }else if (closeMatch(Color.rgb(204, 102, 153), touchColor, tolerance)) {
                    // this is the area for the sylhet
                    DialogArea dialogArea = new DialogArea(EntireMap.this, "sylhet", pollution, unorganized, risk);
                    dialogArea.show();
                }else {
                    Toast error_toast = Toast.makeText(getApplicationContext(), "Select one of these cities", Toast.LENGTH_SHORT);
                    error_toast.show();
                }
                break;
        }
        return true;
    }
    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }
    public boolean closeMatch (int color1, int color2, int tolerance) {
        if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance )
            return false;
        if ((int) Math.abs (Color.green (color1) - Color.green (color2)) > tolerance )
            return false;
        if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance )
            return false;
        return true;
    }

    // handle the go back function
    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), MapFound.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }
}
