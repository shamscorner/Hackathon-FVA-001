package com.fva_001.flashvsarrow.com.fva_001;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ShamimH on 23-Mar-16.
 */
public class LevelRajshahi  extends AppCompatActivity {

    private Button btnMap;
    private ImageView imgDustbin, imgBanLayer1, imgBanLayer2;
    private AnimationDrawable animDustbin;
    private MediaPlayer success_dustbin;
    private ProgressBar progressPollution, progressUnorganized, progressRisk;
    private TextView txtPollution, txtUnorganized, txtRisk, txtTime, txtScore, txtTask, txtTaskRemain;
    private int taskTotal, taskPollution, taskUnorganized;
    private String type = "";

    private ScoreCard scoreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_rajshahi); //set the content view

        //initialization of all music
        success_dustbin = MediaPlayer.create(getApplicationContext(), R.raw.success_drop);

        //initialise all the component of the level background
        imgDustbin = (ImageView)findViewById(R.id.img_dustbin);
        imgBanLayer1 = (ImageView)findViewById(R.id.img_banana_layer_1);
        imgBanLayer1.setTag("bananaLayer1");
        imgBanLayer2 = (ImageView)findViewById(R.id.img_banana_layer_2);
        imgBanLayer2.setTag("bananaLayer2");
        progressPollution = (ProgressBar)findViewById(R.id.pro_lev_pol);
        progressRisk = (ProgressBar)findViewById(R.id.pro_lev_risk);
        progressUnorganized = (ProgressBar)findViewById(R.id.pro_lev_un);
        txtPollution = (TextView)findViewById(R.id.percent_pollution_levview);
        txtRisk = (TextView)findViewById(R.id.percent_risk_levview);
        txtUnorganized = (TextView)findViewById(R.id.percent_unorganized_levview);
        txtTime = (TextView)findViewById(R.id.count_time);
        txtScore = (TextView)findViewById(R.id.count_score);
        txtTask = (TextView)findViewById(R.id.count_task);
        txtTaskRemain = (TextView)findViewById(R.id.count_task_remain);

        //initialize all animation effect
        animDustbin = (AnimationDrawable) imgDustbin.getBackground();

        //initialize all the number of task
        taskPollution = 5;
        taskUnorganized = 7;
        taskTotal = taskPollution + taskUnorganized;

        //initialize the score class
        scoreCard = new ScoreCard(taskPollution, taskUnorganized);

        //counting the countdown
        scoreCard.setTime("1:20");

        //set all the value for this level
        updateScore();

        // this is the map section
        btnMap = (Button)findViewById(R.id.btn_map_in_level);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGoBackToMap dialogGoBackToMap = new DialogGoBackToMap(LevelRajshahi.this);
                dialogGoBackToMap.show();
            }
        });

        // this is the reset handling section
        Button btnReset = (Button)findViewById(R.id.btn_reset_level);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCard = new ScoreCard(taskPollution, taskUnorganized);
                updateScore();
                // reload this current activity
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        //the touch listener for the dragevent
        imgBanLayer1.setOnTouchListener(new DragTouchListener());
        imgBanLayer2.setOnTouchListener(new DragTouchListener());

        //the drop zone of all wast which is the dustbin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            imgDustbin.setOnDragListener(new DustbinDragListener());
        }
    }
    private void updateScore(){
        //set all values to the layout
        progressPollution.setProgress(scoreCard.getPollutionRate());
        progressUnorganized.setProgress(scoreCard.getUnorganizedRate());
        progressRisk.setProgress(scoreCard.getRiskRate());
        txtPollution.setText(scoreCard.getPollutionRate() + "%");
        txtUnorganized.setText(scoreCard.getUnorganizedRate()+"%");
        txtRisk.setText(scoreCard.getRiskRate()+"%");
        txtTime.setText(scoreCard.getTime());
        txtScore.setText(""+scoreCard.getScore());
        txtTask.setText(""+scoreCard.getTaskCompleted());
        txtTaskRemain.setText(""+(taskTotal - scoreCard.getTaskCompleted()));
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        DialogGoBackToMap dialog = new DialogGoBackToMap(LevelRajshahi.this);
        dialog.show();
    }

    //Draglistener class
    private final class DragTouchListener implements View.OnTouchListener{

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                //v.setVisibility(View.INVISIBLE);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }else{
                return false;
            }
        }
    }

    //Droplistener class
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private final class DustbinDragListener implements View.OnDragListener{
        ClipData.Item item;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    success_dustbin.start();
                    animDustbin.setOneShot(true);
                    animDustbin.setVisible(true, true);
                    animDustbin.start();

                    item = event.getClipData().getItemAt(0);
                    if(item.getText().equals("bananaLayer1")){
                        imgBanLayer1.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("bananaLayer2")){
                        imgBanLayer2.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }
                    scoreCard.increaseScore(10, type);
                    updateScore();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    return false;
            }
            return true;
        }
    }
}
