package com.fva_001.flashvsarrow.com.fva_001;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LevelCompleted extends AppCompatActivity {

    private LinearLayout headerHolder;
    private Animation animFadein, animSlideup;
    private LinearLayout objAnimFinal;
    private TextView objDivider;
    private Map<Integer, LinearLayout> objAnim;
    long time = 5000;
    int i = 1;
    private MediaPlayer mplayer;
    //image
    private Bitmap image;
    //counter
    private int counter = 0;

    ProgressBar proPollution, proUnorganized, proRisk;
    TextView percentPol, percentUn, percentRisk, polScore, unScore, riskScore, elapsedTime, timeScore, taskCompleted, taskCompletedScore,
            toolsUsed, toolsUsedScore, score, finalScore, highScoreTag;
    ImageView imgRating1, imgRating2, imgRating3;

    //for storing the data value
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_completed);

        //initialize the sharedpreferences file
        pref = getApplicationContext().getSharedPreferences("core_fva", MODE_PRIVATE);

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

        ////////////// Sound/////////
        mplayer = MediaPlayer.create(LevelCompleted.this, R.raw.score_sound);
        mplayer.setOnCompletionListener(new MediaPlayerRelease());
        mplayer.start();

        int delay;
        for (i = 1; i < 8; i++) {
            delay=(i*90)+1000;
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

        //initialize all the view and resource
        proPollution = (ProgressBar)findViewById(R.id.progressbar_pollution_completed);
        proPollution.setProgress(pref.getInt("PERCENT_POL", 0));
        proUnorganized = (ProgressBar)findViewById(R.id.progressbar_unorganized_completed);
        proUnorganized.setProgress(pref.getInt("PERCENT_UN", 0));
        proRisk = (ProgressBar)findViewById(R.id.progressbar_risk_completed);
        proRisk.setProgress(pref.getInt("PERCENT_RISK", 0));

        percentPol = (TextView)findViewById(R.id.percent_poll);
        percentPol.setText(pref.getInt("PERCENT_POL", 0) + "%");
        percentUn = (TextView)findViewById(R.id.percent_unorg);
        percentUn.setText(pref.getInt("PERCENT_UN", 0)+"%");
        percentRisk = (TextView)findViewById(R.id.percent_risk);
        percentRisk.setText(pref.getInt("PERCENT_RISK", 0)+"%");
        polScore = (TextView)findViewById(R.id.pollution_score);
        polScore.setText("" +pref.getInt("SCORE_POL", 0));
        unScore = (TextView)findViewById(R.id.unorganized_score);
        unScore.setText(""+pref.getInt("SCORE_UN", 0));
        riskScore = (TextView)findViewById(R.id.risk_score);
        riskScore.setText(""+pref.getInt("SCORE_RISK", 0));
        elapsedTime = (TextView)findViewById(R.id.elapsed_time);
        elapsedTime.setText(pref.getString("TIME", ""));
        timeScore = (TextView)findViewById(R.id.elapsed_time_score);
        timeScore.setText(""+pref.getLong("TIME_SCORE", 0));
        taskCompleted = (TextView)findViewById(R.id.task_no);
        taskCompleted.setText(""+pref.getInt("TASK_COMPLETED", 0));
        taskCompletedScore = (TextView)findViewById(R.id.task_no_score);
        taskCompletedScore.setText(""+pref.getInt("TASK_SCORE", 0));
        toolsUsed = (TextView)findViewById(R.id.tools_used_no);
        toolsUsed.setText(""+pref.getInt("TOOLS", 0));
        toolsUsedScore = (TextView)findViewById(R.id.tools_used_no_score);
        toolsUsedScore.setText(""+pref.getInt("TOOLS_SCORE", 0));
        score = (TextView)findViewById(R.id.score_completed);
        score.setText(""+pref.getInt("SCORE", 0));
        finalScore = (TextView)findViewById(R.id.final_score);
        finalScore.setText(""+pref.getInt("FINAL_SCORE", 0));
        highScoreTag = (TextView)findViewById(R.id.text_high_score);
        highScoreTag.setVisibility(View.INVISIBLE);

        imgRating1 = (ImageView)findViewById(R.id.rating1);
        imgRating1.setVisibility(View.INVISIBLE);
        imgRating2 = (ImageView)findViewById(R.id.rating2);
        imgRating2.setVisibility(View.INVISIBLE);
        imgRating3 = (ImageView)findViewById(R.id.rating3);
        imgRating3.setVisibility(View.INVISIBLE);

        int score = pref.getInt("FINAL_SCORE", 0);
        if(score > 1500){
            imgRating1.setVisibility(View.VISIBLE);
            imgRating1.startAnimation(animFadein);
        }
        if(score > 2500){
            imgRating2.setVisibility(View.VISIBLE);
            imgRating2.startAnimation(animFadein);
        }
        if(score > 4000){
            imgRating3.setVisibility(View.VISIBLE);
            imgRating3.startAnimation(animFadein);
        }
        if(score > getIntent().getIntExtra("HIGH_SCORE", 0)){
            highScoreTag.setVisibility(View.VISIBLE);
        }

        // the share in facebook button
        ImageButton btnFacebook = (ImageButton)findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                takeScreenshot();
            }
        });
    }
    //the share section
    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


            MediaScannerConnection.scanFile(this,
                    new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
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
